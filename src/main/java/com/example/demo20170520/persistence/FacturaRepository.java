package com.example.demo20170520.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by domix on 5/20/17.
 */
public interface FacturaRepository
  extends JpaRepository<Factura, Long> {
}
