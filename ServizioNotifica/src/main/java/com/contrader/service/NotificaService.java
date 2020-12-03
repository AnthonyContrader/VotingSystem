package com.contrader.service;

import com.contrader.service.dto.NotificaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.contrader.domain.Notifica}.
 */
public interface NotificaService {

    /**
     * Save a notifica.
     *
     * @param notificaDTO the entity to save.
     * @return the persisted entity.
     */
    NotificaDTO save(NotificaDTO notificaDTO);

    /**
     * Get all the notificas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<NotificaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" notifica.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<NotificaDTO> findOne(Long id);

    /**
     * Delete the "id" notifica.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
