package com.example.demo20170520.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by domix on 5/20/17.
 */
@Setter
@Getter
public class FacturaCommand {
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
}
