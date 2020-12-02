package com.contrader.service;

import com.contrader.service.dto.DomandeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.domain.Domande}.
 */
public interface DomandeService {

    /**
     * Save a domande.
     *
     * @param domandeDTO the entity to save.
     * @return the persisted entity.
     */
    DomandeDTO save(DomandeDTO domandeDTO);

    /**
     * Get all the domandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DomandeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" domande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DomandeDTO> findOne(Long id);

    /**
     * Delete the "id" domande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
