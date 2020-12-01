package com.project.contrader.votomj.web.rest;

import com.project.contrader.votomj.service.impl.VotoServiceImpl;
import com.project.contrader.votomj.web.rest.errors.BadRequestAlertException;
import com.project.contrader.votomj.service.dto.VotoDTO;

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
 * REST controller for managing {@link com.project.contrader.votomj.domain.Voto}.
 */
@RestController
@RequestMapping("/api")
public class VotoResource {

    private final Logger log = LoggerFactory.getLogger(VotoResource.class);

    private static final String ENTITY_NAME = "votoVoto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VotoServiceImpl votoService;

    public VotoResource(VotoServiceImpl votoService) {
        this.votoService = votoService;
    }

    /**
     * {@code POST  /votos} : Create a new voto.
     *
     * @param votoDTO the votoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new votoDTO, or with status {@code 400 (Bad Request)} if the voto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/votos")
    public ResponseEntity<VotoDTO> createVoto(@RequestBody VotoDTO votoDTO) throws URISyntaxException {
        log.debug("REST request to save Voto : {}", votoDTO);
        if (votoDTO.getId() != null) {
            throw new BadRequestAlertException("A new voto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VotoDTO result = votoService.save(votoDTO);
        return ResponseEntity.created(new URI("/api/votos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /votos} : Updates an existing voto.
     *
     * @param votoDTO the votoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated votoDTO,
     * or with status {@code 400 (Bad Request)} if the votoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the votoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/votos")
    public ResponseEntity<VotoDTO> updateVoto(@RequestBody VotoDTO votoDTO) throws URISyntaxException {
        log.debug("REST request to update Voto : {}", votoDTO);
        if (votoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VotoDTO result = votoService.save(votoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, votoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /votos} : get all the votos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of votos in body.
     */
    @GetMapping("/votos")
    public ResponseEntity<List<VotoDTO>> getAllVotos(Pageable pageable) {
        log.debug("REST request to get a page of Votos");
        Page<VotoDTO> page = votoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /votos/:id} : get the "id" voto.
     *
     * @param id the id of the votoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the votoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/votos/{id}")
    public ResponseEntity<VotoDTO> getVoto(@PathVariable Long id) {
        log.debug("REST request to get Voto : {}", id);
        Optional<VotoDTO> votoDTO = votoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(votoDTO);
    }

    /**
     * {@code DELETE  /votos/:id} : delete the "id" voto.
     *
     * @param id the id of the votoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/votos/{id}")
    public ResponseEntity<Void> deleteVoto(@PathVariable Long id) {
        log.debug("REST request to delete Voto : {}", id);
        votoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
