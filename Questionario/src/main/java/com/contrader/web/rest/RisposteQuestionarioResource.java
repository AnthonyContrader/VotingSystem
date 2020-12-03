package com.contrader.web.rest;

import com.contrader.service.RisposteQuestionarioService;
import com.contrader.web.rest.errors.BadRequestAlertException;
import com.contrader.service.dto.RisposteQuestionarioDTO;

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
 * REST controller for managing {@link com.contrader.domain.RisposteQuestionario}.
 */
@RestController
@RequestMapping("/api")
public class RisposteQuestionarioResource {

    private final Logger log = LoggerFactory.getLogger(RisposteQuestionarioResource.class);

    private static final String ENTITY_NAME = "questionarioRisposteQuestionario";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RisposteQuestionarioService risposteQuestionarioService;

    public RisposteQuestionarioResource(RisposteQuestionarioService risposteQuestionarioService) {
        this.risposteQuestionarioService = risposteQuestionarioService;
    }

    /**
     * {@code POST  /risposte-questionarios} : Create a new risposteQuestionario.
     *
     * @param risposteQuestionarioDTO the risposteQuestionarioDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new risposteQuestionarioDTO, or with status {@code 400 (Bad Request)} if the risposteQuestionario has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risposte-questionarios")
    public ResponseEntity<RisposteQuestionarioDTO> createRisposteQuestionario(@RequestBody RisposteQuestionarioDTO risposteQuestionarioDTO) throws URISyntaxException {
        log.debug("REST request to save RisposteQuestionario : {}", risposteQuestionarioDTO);
        if (risposteQuestionarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new risposteQuestionario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RisposteQuestionarioDTO result = risposteQuestionarioService.save(risposteQuestionarioDTO);
        return ResponseEntity.created(new URI("/api/risposte-questionarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risposte-questionarios} : Updates an existing risposteQuestionario.
     *
     * @param risposteQuestionarioDTO the risposteQuestionarioDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated risposteQuestionarioDTO,
     * or with status {@code 400 (Bad Request)} if the risposteQuestionarioDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the risposteQuestionarioDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risposte-questionarios")
    public ResponseEntity<RisposteQuestionarioDTO> updateRisposteQuestionario(@RequestBody RisposteQuestionarioDTO risposteQuestionarioDTO) throws URISyntaxException {
        log.debug("REST request to update RisposteQuestionario : {}", risposteQuestionarioDTO);
        if (risposteQuestionarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RisposteQuestionarioDTO result = risposteQuestionarioService.save(risposteQuestionarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, risposteQuestionarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risposte-questionarios} : get all the risposteQuestionarios.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of risposteQuestionarios in body.
     */
    @GetMapping("/risposte-questionarios")
    public ResponseEntity<List<RisposteQuestionarioDTO>> getAllRisposteQuestionarios(Pageable pageable) {
        log.debug("REST request to get a page of RisposteQuestionarios");
        Page<RisposteQuestionarioDTO> page = risposteQuestionarioService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risposte-questionarios/:id} : get the "id" risposteQuestionario.
     *
     * @param id the id of the risposteQuestionarioDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the risposteQuestionarioDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risposte-questionarios/{id}")
    public ResponseEntity<RisposteQuestionarioDTO> getRisposteQuestionario(@PathVariable Long id) {
        log.debug("REST request to get RisposteQuestionario : {}", id);
        Optional<RisposteQuestionarioDTO> risposteQuestionarioDTO = risposteQuestionarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(risposteQuestionarioDTO);
    }

    /**
     * {@code DELETE  /risposte-questionarios/:id} : delete the "id" risposteQuestionario.
     *
     * @param id the id of the risposteQuestionarioDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risposte-questionarios/{id}")
    public ResponseEntity<Void> deleteRisposteQuestionario(@PathVariable Long id) {
        log.debug("REST request to delete RisposteQuestionario : {}", id);
        risposteQuestionarioService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
