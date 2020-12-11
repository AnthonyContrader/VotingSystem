package com.contrader.microservizioscheda.repository;

import com.contrader.microservizioscheda.domain.Scheda;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Scheda entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SchedaRepository extends JpaRepository<Scheda, Long> {
}
