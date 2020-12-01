package com.project.contrader.votomj.web.rest;

import com.project.contrader.votomj.VotoApp;
import com.project.contrader.votomj.domain.Voto;
import com.project.contrader.votomj.repository.VotoRepository;
import com.project.contrader.votomj.service.VotoService;
import com.project.contrader.votomj.service.dto.VotoDTO;
import com.project.contrader.votomj.service.mapper.VotoMapper;

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
 * Integration tests for the {@link VotoResource} REST controller.
 */
@SpringBootTest(classes = VotoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VotoResourceIT {

    private static final Long DEFAULT_ID_UTENTE = 1L;
    private static final Long UPDATED_ID_UTENTE = 2L;

    private static final Long DEFAULT_ID_SCHEDA = 1L;
    private static final Long UPDATED_ID_SCHEDA = 2L;

    private static final Long DEFAULT_VOTO = 1L;
    private static final Long UPDATED_VOTO = 2L;

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private VotoMapper votoMapper;

    @Autowired
    private VotoService votoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVotoMockMvc;

    private Voto voto;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Voto createEntity(EntityManager em) {
        Voto voto = new Voto()
            .idUtente(DEFAULT_ID_UTENTE)
            .idScheda(DEFAULT_ID_SCHEDA)
            .voto(DEFAULT_VOTO);
        return voto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Voto createUpdatedEntity(EntityManager em) {
        Voto voto = new Voto()
            .idUtente(UPDATED_ID_UTENTE)
            .idScheda(UPDATED_ID_SCHEDA)
            .voto(UPDATED_VOTO);
        return voto;
    }

    @BeforeEach
    public void initTest() {
        voto = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoto() throws Exception {
        int databaseSizeBeforeCreate = votoRepository.findAll().size();
        // Create the Voto
        VotoDTO votoDTO = votoMapper.toDto(voto);
        restVotoMockMvc.perform(post("/api/votos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(votoDTO)))
            .andExpect(status().isCreated());

        // Validate the Voto in the database
        List<Voto> votoList = votoRepository.findAll();
        assertThat(votoList).hasSize(databaseSizeBeforeCreate + 1);
        Voto testVoto = votoList.get(votoList.size() - 1);
        assertThat(testVoto.getIdUtente()).isEqualTo(DEFAULT_ID_UTENTE);
        assertThat(testVoto.getIdScheda()).isEqualTo(DEFAULT_ID_SCHEDA);
        assertThat(testVoto.getVoto()).isEqualTo(DEFAULT_VOTO);
    }

    @Test
    @Transactional
    public void createVotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = votoRepository.findAll().size();

        // Create the Voto with an existing ID
        voto.setId(1L);
        VotoDTO votoDTO = votoMapper.toDto(voto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVotoMockMvc.perform(post("/api/votos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(votoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Voto in the database
        List<Voto> votoList = votoRepository.findAll();
        assertThat(votoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVotos() throws Exception {
        // Initialize the database
        votoRepository.saveAndFlush(voto);

        // Get all the votoList
        restVotoMockMvc.perform(get("/api/votos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voto.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUtente").value(hasItem(DEFAULT_ID_UTENTE.intValue())))
            .andExpect(jsonPath("$.[*].idScheda").value(hasItem(DEFAULT_ID_SCHEDA.intValue())))
            .andExpect(jsonPath("$.[*].voto").value(hasItem(DEFAULT_VOTO.intValue())));
    }
    
    @Test
    @Transactional
    public void getVoto() throws Exception {
        // Initialize the database
        votoRepository.saveAndFlush(voto);

        // Get the voto
        restVotoMockMvc.perform(get("/api/votos/{id}", voto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(voto.getId().intValue()))
            .andExpect(jsonPath("$.idUtente").value(DEFAULT_ID_UTENTE.intValue()))
            .andExpect(jsonPath("$.idScheda").value(DEFAULT_ID_SCHEDA.intValue()))
            .andExpect(jsonPath("$.voto").value(DEFAULT_VOTO.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingVoto() throws Exception {
        // Get the voto
        restVotoMockMvc.perform(get("/api/votos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoto() throws Exception {
        // Initialize the database
        votoRepository.saveAndFlush(voto);

        int databaseSizeBeforeUpdate = votoRepository.findAll().size();

        // Update the voto
        Voto updatedVoto = votoRepository.findById(voto.getId()).get();
        // Disconnect from session so that the updates on updatedVoto are not directly saved in db
        em.detach(updatedVoto);
        updatedVoto
            .idUtente(UPDATED_ID_UTENTE)
            .idScheda(UPDATED_ID_SCHEDA)
            .voto(UPDATED_VOTO);
        VotoDTO votoDTO = votoMapper.toDto(updatedVoto);

        restVotoMockMvc.perform(put("/api/votos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(votoDTO)))
            .andExpect(status().isOk());

        // Validate the Voto in the database
        List<Voto> votoList = votoRepository.findAll();
        assertThat(votoList).hasSize(databaseSizeBeforeUpdate);
        Voto testVoto = votoList.get(votoList.size() - 1);
        assertThat(testVoto.getIdUtente()).isEqualTo(UPDATED_ID_UTENTE);
        assertThat(testVoto.getIdScheda()).isEqualTo(UPDATED_ID_SCHEDA);
        assertThat(testVoto.getVoto()).isEqualTo(UPDATED_VOTO);
    }

    @Test
    @Transactional
    public void updateNonExistingVoto() throws Exception {
        int databaseSizeBeforeUpdate = votoRepository.findAll().size();

        // Create the Voto
        VotoDTO votoDTO = votoMapper.toDto(voto);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVotoMockMvc.perform(put("/api/votos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(votoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Voto in the database
        List<Voto> votoList = votoRepository.findAll();
        assertThat(votoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVoto() throws Exception {
        // Initialize the database
        votoRepository.saveAndFlush(voto);

        int databaseSizeBeforeDelete = votoRepository.findAll().size();

        // Delete the voto
        restVotoMockMvc.perform(delete("/api/votos/{id}", voto.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Voto> votoList = votoRepository.findAll();
        assertThat(votoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
