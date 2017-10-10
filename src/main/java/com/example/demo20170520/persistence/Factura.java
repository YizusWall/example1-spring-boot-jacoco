package com.example.demo20170520.persistence;

import com.example.demo20170520.model.FacturaCommand;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by domix on 5/20/17.
 */
@Entity
@Getter
@Setter
public class Factura {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  @Past
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date fecha;
  @NotBlank
  @Size(min = 11, max = 11)
  private String ruc;
  @NotBlank
  private String razonSocial;
  @NotNull
  @Min(0)
  private BigDecimal montoTotal;
  @Min(0)
  private BigDecimal subTotal;
  @Min(0)
  private BigDecimal igv;
  @NotBlank
  private String concepto;

  public static Factura from(FacturaCommand command) {
    Factura factura = new Factura();
    BeanUtils.copyProperties(command, factura);

    return factura;
  }

  public static FacturaCommand from(Factura factura) {
    FacturaCommand command = new FacturaCommand();
    BeanUtils.copyProperties(factura, command);

    return command;
  }
}
