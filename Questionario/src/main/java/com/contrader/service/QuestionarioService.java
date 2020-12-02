package com.contrader.service;

import com.contrader.service.dto.QuestionarioDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.domain.Questionario}.
 */
public interface QuestionarioService {

    /**
     * Save a questionario.
     *
     * @param questionarioDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionarioDTO save(QuestionarioDTO questionarioDTO);

    /**
     * Get all the questionarios.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionarioDTO> findAll(Pageable pageable);


    /**
     * Get the "id" questionario.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionarioDTO> findOne(Long id);

    /**
     * Delete the "id" questionario.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
