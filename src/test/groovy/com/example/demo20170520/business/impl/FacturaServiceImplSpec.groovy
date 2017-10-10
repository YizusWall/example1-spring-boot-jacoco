package com.example.demo20170520.business.impl

import com.example.demo20170520.business.FacturaBusinessException
import com.example.demo20170520.business.ValidacionService
import com.example.demo20170520.business.ValidationException
import com.example.demo20170520.model.FacturaCommand
import com.example.demo20170520.persistence.Factura
import com.example.demo20170520.persistence.FacturaRepository
import org.springframework.beans.BeanUtils
import spock.lang.Specification

/**
 * Created by domix on 5/20/17.
 */
class FacturaServiceImplSpec extends Specification {

  def 'deberia guardar una factura con el camino feliz'() {
    given:
      ValidacionService validacionService = Mock()
      FacturaRepository facturaRepository = Stub()
      FacturaCommand command = new FacturaCommand(
        fecha: new Date(),
        ruc: '12345678901',
        razonSocial: 'Algo',
        montoTotal: 30,
        subTotal: 20,
        igv: 10,
        concepto: 'Un producto'
      )

      facturaRepository.save(_) >> {
        Factura factura = new Factura()
        BeanUtils.copyProperties(command, factura)
        factura.id = 1L
        return factura
      }
      FacturaServiceImpl service = new FacturaServiceImpl(validacionService, facturaRepository)


      FacturaCommand factura = service.guardarFactura(command)
    expect:
      factura
  }

  def 'deberia fallar al enviar una factura en NULL'() {
    when:
      ValidacionService validacionService = Mock()
      FacturaRepository facturaRepository = Mock()
      FacturaServiceImpl service = new FacturaServiceImpl(validacionService, facturaRepository)
      service.guardarFactura(null)
    then:
      IllegalArgumentException error = thrown()
      error
      error.message == 'La factura es requerida'
  }

  def 'deberia fallar al intentar guardar una factura con los montos incorrectos'() {
    when:
      ValidacionService validacionService = Mock()
      FacturaRepository facturaRepository = Mock()
      FacturaServiceImpl service = new FacturaServiceImpl(validacionService, facturaRepository)
      FacturaCommand command = new FacturaCommand(
        fecha: new Date(),
        ruc: '12345678901',
        razonSocial: 'Algo',
        montoTotal: 30,
        subTotal: 20,
        igv: 0,
        concepto: 'Un producto'
      )

      service.guardarFactura(command)
    then:
      FacturaBusinessException error = thrown()
      error.message == FacturaServiceImpl.ERROR_MONTO
      error.factura
      println error.factura.dump()
  }
}
