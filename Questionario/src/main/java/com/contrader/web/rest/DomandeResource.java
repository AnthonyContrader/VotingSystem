package com.contrader.web.rest;

import com.contrader.service.DomandeService;
import com.contrader.web.rest.errors.BadRequestAlertException;
import com.contrader.service.dto.DomandeDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.contrader.domain.Domande}.
 */
@RestController
@RequestMapping("/api")
public class DomandeResource {

    private final Logger log = LoggerFactory.getLogger(DomandeResource.class);

    private static final String ENTITY_NAME = "questionarioDomande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DomandeService domandeService;

    public DomandeResource(DomandeService domandeService) {
        this.domandeService = domandeService;
    }

    /**
     * {@code POST  /domandes} : Create a new domande.
     *
     * @param domandeDTO the domandeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new domandeDTO, or with status {@code 400 (Bad Request)} if the domande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/domandes")
    public ResponseEntity<DomandeDTO> createDomande(@RequestBody DomandeDTO domandeDTO) throws URISyntaxException {
        log.debug("REST request to save Domande : {}", domandeDTO);
        if (domandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new domande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DomandeDTO result = domandeService.save(domandeDTO);
        return ResponseEntity.created(new URI("/api/domandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /domandes} : Updates an existing domande.
     *
     * @param domandeDTO the domandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated domandeDTO,
     * or with status {@code 400 (Bad Request)} if the domandeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the domandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/domandes")
    public ResponseEntity<DomandeDTO> updateDomande(@RequestBody DomandeDTO domandeDTO) throws URISyntaxException {
        log.debug("REST request to update Domande : {}", domandeDTO);
        if (domandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DomandeDTO result = domandeService.save(domandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, domandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /domandes} : get all the domandes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domandes in body.
     */
    @GetMapping("/domandes")
    public ResponseEntity<List<DomandeDTO>> getAllDomandes(Pageable pageable) {
        log.debug("REST request to get a page of Domandes");
        Page<DomandeDTO> page = domandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /domandes/:id} : get the "id" domande.
     *
     * @param id the id of the domandeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domandeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domandes/{id}")
    public ResponseEntity<DomandeDTO> getDomande(@PathVariable Long id) {
        log.debug("REST request to get Domande : {}", id);
        Optional<DomandeDTO> domandeDTO = domandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domandeDTO);
    }

    /**
     * {@code DELETE  /domandes/:id} : delete the "id" domande.
     *
     * @param id the id of the domandeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/domandes/{id}")
    public ResponseEntity<Void> deleteDomande(@PathVariable Long id) {
        log.debug("REST request to delete Domande : {}", id);
        domandeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
