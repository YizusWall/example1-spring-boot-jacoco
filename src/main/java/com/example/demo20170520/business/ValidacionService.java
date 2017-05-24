package com.example.demo20170520.business;

import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by domix on 5/20/17.
 */
@Service
public class ValidacionService {
  private final Validator validator;

  public ValidacionService(Validator validator) {
    this.validator = validator;
  }

  public void validate(Object object, String message) throws ValidationException {
    Set<ConstraintViolation<Object>> violations = validator.validate(object);

    if (violations.size() > 0) {
      String errorMesage = message != null ? message : "Error validando objeto";
      throw new ValidationException(errorMesage, violations);
    }
  }
}
