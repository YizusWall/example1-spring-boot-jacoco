package com.example.demo20170520.business.impl;

import com.example.demo20170520.business.FacturaBusinessException;
import com.example.demo20170520.business.FacturaService;
import com.example.demo20170520.business.ValidacionService;
import com.example.demo20170520.model.FacturaCommand;
import com.example.demo20170520.persistence.Factura;
import com.example.demo20170520.persistence.FacturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by domix on 5/20/17.
 */
@Service
public class FacturaServiceImpl implements FacturaService {

  public static final String ERROR_MONTO = "El monto total difiere del subtotal + IGV.";
  private final ValidacionService validacionService;
  private final FacturaRepository facturaRepository;

  public FacturaServiceImpl(ValidacionService validacionService, FacturaRepository facturaRepository) {
    this.validacionService = validacionService;
    this.facturaRepository = facturaRepository;
  }

  @Override
  public FacturaCommand guardarFactura(FacturaCommand facturaCommand) {
    String message = "La factura es requerida";

    Assert.notNull(facturaCommand, message);

    validacionService.validate(facturaCommand, "Revisa la factura, tiene errores");

    if (!facturaCommand.getMontoTotal()
      .equals(facturaCommand.getIgv()
        .add(facturaCommand.getSubTotal()))) {
      throw new FacturaBusinessException(facturaCommand, ERROR_MONTO);
    }

    Factura factura = Factura.from(facturaCommand);

    return Factura.from(facturaRepository.save(factura));
  }
}
