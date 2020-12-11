package com.contrader.microservizioscheda.web.rest;

import com.contrader.microservizioscheda.service.impl.SchedaServiceImpl;
import com.contrader.microservizioscheda.web.rest.errors.BadRequestAlertException;
import com.contrader.microservizioscheda.service.dto.SchedaDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.contrader.microservizioscheda.domain.Scheda}.
 */
@RestController
@RequestMapping("/api")
public class SchedaResource {

    private final Logger log = LoggerFactory.getLogger(SchedaResource.class);

    private static final String ENTITY_NAME = "schedaScheda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SchedaServiceImpl schedaService;

    public SchedaResource(SchedaServiceImpl schedaService) {
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
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
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
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, schedaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /schedas} : get all the schedas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of schedas in body.
     */
    @GetMapping("/schedas")
    public ResponseEntity<List<SchedaDTO>> getAllSchedas(Pageable pageable) {
        log.debug("REST request to get a page of Schedas");
        Page<SchedaDTO> page = schedaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/schedas/schede-utente/{id_utente}")
    public ResponseEntity<List<SchedaDTO>> getSchedaByIdUtente(HttpServletRequest request, HttpServletResponse httpresponse,@PathVariable Long id_utente) {
    	RestTemplate restt = new RestTemplate();
    	String bearerToken = request.getHeader("Authorization");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", bearerToken);
        
        HttpEntity<String> httpEntity = new HttpEntity <String> (httpHeaders);
    	
    	String url = "http://localhost:8080/services/voto/api/votos/schede-voto-utente/" + id_utente.toString();
    	List<SchedaDTO> list = schedaService.findAll();
    	ResponseEntity<Long[]> response = restt.exchange(url, HttpMethod.GET, httpEntity, Long[].class);
                
                
    	Long[] listIdSchede = response.getBody();
    	
    	for(int i = 0; i < list.size(); i++)
    		for(int j = 0; j < listIdSchede.length; j++)
    			if(list.get(i).getId() == listIdSchede[j])
    				list.get(i).setVotato(true);
    		
    			
    	
    	return ResponseEntity.ok().body(list);
    }
    
}
