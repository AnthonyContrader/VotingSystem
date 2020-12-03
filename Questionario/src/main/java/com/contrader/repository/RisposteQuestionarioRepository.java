package com.contrader.repository;

import com.contrader.domain.RisposteQuestionario;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RisposteQuestionario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RisposteQuestionarioRepository extends JpaRepository<RisposteQuestionario, Long> {
}
