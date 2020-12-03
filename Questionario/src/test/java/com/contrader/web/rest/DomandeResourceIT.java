package com.contrader.web.rest;

import com.contrader.QuestionarioApp;
import com.contrader.domain.Domande;
import com.contrader.repository.DomandeRepository;
import com.contrader.service.DomandeService;
import com.contrader.service.dto.DomandeDTO;
import com.contrader.service.mapper.DomandeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DomandeResource} REST controller.
 */
@SpringBootTest(classes = QuestionarioApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DomandeResourceIT {

    private static final String DEFAULT_TESTO = "AAAAAAAAAA";
    private static final String UPDATED_TESTO = "BBBBBBBBBB";

    @Autowired
    private DomandeRepository domandeRepository;

    @Autowired
    private DomandeMapper domandeMapper;

    @Autowired
    private DomandeService domandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDomandeMockMvc;

    private Domande domande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domande createEntity(EntityManager em) {
        Domande domande = new Domande()
            .testo(DEFAULT_TESTO);
        return domande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Domande createUpdatedEntity(EntityManager em) {
        Domande domande = new Domande()
            .testo(UPDATED_TESTO);
        return domande;
    }

    @BeforeEach
    public void initTest() {
        domande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDomande() throws Exception {
        int databaseSizeBeforeCreate = domandeRepository.findAll().size();
        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);
        restDomandeMockMvc.perform(post("/api/domandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeCreate + 1);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getTesto()).isEqualTo(DEFAULT_TESTO);
    }

    @Test
    @Transactional
    public void createDomandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = domandeRepository.findAll().size();

        // Create the Domande with an existing ID
        domande.setId(1L);
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDomandeMockMvc.perform(post("/api/domandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDomandes() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        // Get all the domandeList
        restDomandeMockMvc.perform(get("/api/domandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domande.getId().intValue())))
            .andExpect(jsonPath("$.[*].testo").value(hasItem(DEFAULT_TESTO)));
    }
    
    @Test
    @Transactional
    public void getDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        // Get the domande
        restDomandeMockMvc.perform(get("/api/domandes/{id}", domande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(domande.getId().intValue()))
            .andExpect(jsonPath("$.testo").value(DEFAULT_TESTO));
    }
    @Test
    @Transactional
    public void getNonExistingDomande() throws Exception {
        // Get the domande
        restDomandeMockMvc.perform(get("/api/domandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();

        // Update the domande
        Domande updatedDomande = domandeRepository.findById(domande.getId()).get();
        // Disconnect from session so that the updates on updatedDomande are not directly saved in db
        em.detach(updatedDomande);
        updatedDomande
            .testo(UPDATED_TESTO);
        DomandeDTO domandeDTO = domandeMapper.toDto(updatedDomande);

        restDomandeMockMvc.perform(put("/api/domandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isOk());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
        Domande testDomande = domandeList.get(domandeList.size() - 1);
        assertThat(testDomande.getTesto()).isEqualTo(UPDATED_TESTO);
    }

    @Test
    @Transactional
    public void updateNonExistingDomande() throws Exception {
        int databaseSizeBeforeUpdate = domandeRepository.findAll().size();

        // Create the Domande
        DomandeDTO domandeDTO = domandeMapper.toDto(domande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDomandeMockMvc.perform(put("/api/domandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(domandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Domande in the database
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDomande() throws Exception {
        // Initialize the database
        domandeRepository.saveAndFlush(domande);

        int databaseSizeBeforeDelete = domandeRepository.findAll().size();

        // Delete the domande
        restDomandeMockMvc.perform(delete("/api/domandes/{id}", domande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Domande> domandeList = domandeRepository.findAll();
        assertThat(domandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
