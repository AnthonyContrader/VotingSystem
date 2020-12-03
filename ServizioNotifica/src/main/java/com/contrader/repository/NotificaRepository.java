package com.contrader.repository;

import com.contrader.domain.Notifica;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Notifica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificaRepository extends JpaRepository<Notifica, Long> {
}
