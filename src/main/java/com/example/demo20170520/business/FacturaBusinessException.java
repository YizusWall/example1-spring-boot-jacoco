package com.example.demo20170520.business;

import com.example.demo20170520.model.FacturaCommand;
import lombok.Getter;

/**
 * Created by domix on 5/20/17.
 */
public class FacturaBusinessException extends RuntimeException {
  @Getter
  private final FacturaCommand factura;

  public FacturaBusinessException(FacturaCommand factura, String message) {
    super(message);
    this.factura = factura;
  }
}
