package com.contrader.microservizioscheda.service;

import com.contrader.microservizioscheda.service.dto.SchedaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.microservizioscheda.domain.Scheda}.
 */
public interface SchedaService {

    /**
     * Save a scheda.
     *
     * @param schedaDTO the entity to save.
     * @return the persisted entity.
     */
    SchedaDTO save(SchedaDTO schedaDTO);

    /**
     * Get all the schedas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SchedaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" scheda.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SchedaDTO> findOne(Long id);

    /**
     * Delete the "id" scheda.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
