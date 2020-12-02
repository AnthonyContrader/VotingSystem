package it.contrader.schedamicroservice.web.rest;

import it.contrader.schedamicroservice.service.SchedaService;
import it.contrader.schedamicroservice.web.rest.errors.BadRequestAlertException;
import it.contrader.schedamicroservice.service.dto.SchedaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link it.contrader.schedamicroservice.domain.Scheda}.
 */
@RestController
@RequestMapping("/api")
public class SchedaResource {

    private final Logger log = LoggerFactory.getLogger(SchedaResource.class);

    private static final String ENTITY_NAME = "schedaScheda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SchedaService schedaService;

    public SchedaResource(SchedaService schedaService) {
        this.schedaService = schedaService;
    }

    /**
     * {@code POST  /schedas} : Create a new scheda.
     *
     * @param schedaDTO the schedaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new schedaDTO, or with status {@code 400 (Bad Request)} if the scheda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/schedas")
    public ResponseEntity<SchedaDTO> createScheda(@Valid @RequestBody SchedaDTO schedaDTO) throws URISyntaxException {
        log.debug("REST request to save Scheda : {}", schedaDTO);
        if (schedaDTO.getId() != null) {
            throw new BadRequestAlertException("A new scheda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SchedaDTO result = schedaService.save(schedaDTO);
        return ResponseEntity.created(new URI("/api/schedas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /schedas} : Updates an existing scheda.
     *
     * @param schedaDTO the schedaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated schedaDTO,
     * or with status {@code 400 (Bad Request)} if the schedaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the schedaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/schedas")
    public ResponseEntity<SchedaDTO> updateScheda(@Valid @RequestBody SchedaDTO schedaDTO) throws URISyntaxException {
        log.debug("REST request to update Scheda : {}", schedaDTO);
        if (schedaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SchedaDTO result = schedaService.save(schedaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, schedaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedas} : get all the schedas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schedas in body.
     */
    @GetMapping("/schedas")
    public List<SchedaDTO> getAllSchedas() {
        log.debug("REST request to get all Schedas");
        return schedaService.findAll();
    }

    /**
     * {@code GET  /schedas/:id} : get the "id" scheda.
     *
     * @param id the id of the schedaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the schedaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/schedas/{id}")
    public ResponseEntity<SchedaDTO> getScheda(@PathVariable Long id) {
        log.debug("REST request to get Scheda : {}", id);
        Optional<SchedaDTO> schedaDTO = schedaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(schedaDTO);
    }

    /**
     * {@code DELETE  /schedas/:id} : delete the "id" scheda.
     *
     * @param id the id of the schedaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/schedas/{id}")
    public ResponseEntity<Void> deleteScheda(@PathVariable Long id) {
        log.debug("REST request to delete Scheda : {}", id);
        schedaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
    /**
     * {@code DELETE  /schedas/:id} : delete the "id" scheda.
     *
     * @param id the id of the schedaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @RequestMapping(value="/schedas/{id}", method=RequestMethod.HEAD)
    public ResponseEntity<Void> existsScheda(@PathVariable Long id) {
        log.debug("Getting existance of Scheda : {}", id);
        if(schedaService.exists(id)) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }
}
