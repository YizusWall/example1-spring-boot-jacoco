package com.example.demo20170520.business;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by domix on 5/20/17.
 */
public class ValidationException extends RuntimeException {
  @Getter
  private Set<ConstraintViolation<Object>> constraintViolations;

  public ValidationException(Set<ConstraintViolation<Object>> constraintViolations) {
    this("Error de validation", constraintViolations);
  }

  public ValidationException(String message, Set<ConstraintViolation<Object>> constraintViolations) {
    super(message);
    this.constraintViolations = constraintViolations;
  }
}
