package com.contrader.repository;

import com.contrader.domain.Domande;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Domande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomandeRepository extends JpaRepository<Domande, Long> {
}
