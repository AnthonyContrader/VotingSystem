package com.contrader.repository;

import com.contrader.domain.Questionario;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Questionario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {
}
