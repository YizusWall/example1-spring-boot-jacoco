package com.example.demo20170520.web;

import com.example.demo20170520.business.FacturaBusinessException;
import com.example.demo20170520.business.ValidationException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.rest.webmvc.support.ConstraintViolationMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by domix on 5/20/17.
 */
@RestControllerAdvice
public class ExceptionHandler {

  private final MessageSource messageSource;

  public ExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @org.springframework.web.bind.annotation.ExceptionHandler(FacturaBusinessException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map exc(FacturaBusinessException errror) {
    Map reslt = new HashMap();
    reslt.put("factura", errror.getFactura());
    reslt.put("mensaje", errror.getMessage());
    return reslt;
  }


  @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public Map validationException(ValidationException errror) {
    Map reslt = new HashMap();
    reslt.put("errores", fromValidationException(errror));
    reslt.put("mensaje", errror.getMessage());
    return reslt;
  }


  private List<ConstraintViolationMessage> fromValidationException(ValidationException exception) {
    Locale locale = LocaleContextHolder.getLocale();

    return exception.getConstraintViolations().stream()
      .map(constraintViolations ->
        new ConstraintViolationMessage(
          constraintViolations,
          messageSource, locale))
      .collect(Collectors.toList());
  }
}
