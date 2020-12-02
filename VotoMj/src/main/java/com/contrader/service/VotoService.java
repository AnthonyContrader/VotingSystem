package com.contrader.service;

import com.contrader.service.dto.VotoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.domain.Voto}.
 */
public interface VotoService {

    /**
     * Save a voto.
     *
     * @param votoDTO the entity to save.
     * @return the persisted entity.
     */
    VotoDTO save(VotoDTO votoDTO);

    /**
     * Get all the votos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VotoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" voto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VotoDTO> findOne(Long id);

    /**
     * Delete the "id" voto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
