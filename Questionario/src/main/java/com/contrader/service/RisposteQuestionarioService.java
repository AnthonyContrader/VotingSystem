package com.contrader.service;

import com.contrader.service.dto.RisposteQuestionarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.domain.RisposteQuestionario}.
 */
public interface RisposteQuestionarioService {

    /**
     * Save a risposteQuestionario.
     *
     * @param risposteQuestionarioDTO the entity to save.
     * @return the persisted entity.
     */
    RisposteQuestionarioDTO save(RisposteQuestionarioDTO risposteQuestionarioDTO);

    /**
     * Get all the risposteQuestionarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RisposteQuestionarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" risposteQuestionario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RisposteQuestionarioDTO> findOne(Long id);

    /**
     * Delete the "id" risposteQuestionario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
