package it.contrader.schedamicroservice.service;

import it.contrader.schedamicroservice.service.dto.SchedaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link it.contrader.schedamicroservice.domain.Scheda}.
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
     * @return the list of entities.
     */
    List<SchedaDTO> findAll();


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
