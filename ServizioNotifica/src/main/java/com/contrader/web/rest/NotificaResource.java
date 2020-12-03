package com.contrader.web.rest;

import com.contrader.service.NotificaService;
import com.contrader.web.rest.errors.BadRequestAlertException;
import com.contrader.service.dto.NotificaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.contrader.domain.Notifica}.
 */
@RestController
@RequestMapping("/api")
public class NotificaResource {

    private final Logger log = LoggerFactory.getLogger(NotificaResource.class);

    private static final String ENTITY_NAME = "servizioNotificaNotifica";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NotificaService notificaService;

    public NotificaResource(NotificaService notificaService) {
        this.notificaService = notificaService;
    }

    /**
     * {@code POST  /notificas} : Create a new notifica.
     *
     * @param notificaDTO the notificaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new notificaDTO, or with status {@code 400 (Bad Request)} if the notifica has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/notificas")
    public ResponseEntity<NotificaDTO> createNotifica(@Valid @RequestBody NotificaDTO notificaDTO) throws URISyntaxException {
        log.debug("REST request to save Notifica : {}", notificaDTO);
        if (notificaDTO.getId() != null) {
            throw new BadRequestAlertException("A new notifica cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NotificaDTO result = notificaService.save(notificaDTO);
        return ResponseEntity.created(new URI("/api/notificas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /notificas} : Updates an existing notifica.
     *
     * @param notificaDTO the notificaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated notificaDTO,
     * or with status {@code 400 (Bad Request)} if the notificaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the notificaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/notificas")
    public ResponseEntity<NotificaDTO> updateNotifica(@Valid @RequestBody NotificaDTO notificaDTO) throws URISyntaxException {
        log.debug("REST request to update Notifica : {}", notificaDTO);
        if (notificaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NotificaDTO result = notificaService.save(notificaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, notificaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /notificas} : get all the notificas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of notificas in body.
     */
    @GetMapping("/notificas")
    public ResponseEntity<List<NotificaDTO>> getAllNotificas(Pageable pageable) {
        log.debug("REST request to get a page of Notificas");
        Page<NotificaDTO> page = notificaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /notificas/:id} : get the "id" notifica.
     *
     * @param id the id of the notificaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the notificaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/notificas/{id}")
    public ResponseEntity<NotificaDTO> getNotifica(@PathVariable Long id) {
        log.debug("REST request to get Notifica : {}", id);
        Optional<NotificaDTO> notificaDTO = notificaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(notificaDTO);
    }

    /**
     * {@code DELETE  /notificas/:id} : delete the "id" notifica.
     *
     * @param id the id of the notificaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/notificas/{id}")
    public ResponseEntity<Void> deleteNotifica(@PathVariable Long id) {
        log.debug("REST request to delete Notifica : {}", id);
        notificaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
