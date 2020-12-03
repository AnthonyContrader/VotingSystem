package com.contrader.web.rest;

import com.contrader.QuestionarioApp;
import com.contrader.domain.RisposteQuestionario;
import com.contrader.repository.RisposteQuestionarioRepository;
import com.contrader.service.RisposteQuestionarioService;
import com.contrader.service.dto.RisposteQuestionarioDTO;
import com.contrader.service.mapper.RisposteQuestionarioMapper;

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
 * Integration tests for the {@link RisposteQuestionarioResource} REST controller.
 */
@SpringBootTest(classes = QuestionarioApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RisposteQuestionarioResourceIT {

    private static final Long DEFAULT_ID_UTENTE = 1L;
    private static final Long UPDATED_ID_UTENTE = 2L;

    private static final String DEFAULT_TESTO = "AAAAAAAAAA";
    private static final String UPDATED_TESTO = "BBBBBBBBBB";

    @Autowired
    private RisposteQuestionarioRepository risposteQuestionarioRepository;

    @Autowired
    private RisposteQuestionarioMapper risposteQuestionarioMapper;

    @Autowired
    private RisposteQuestionarioService risposteQuestionarioService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRisposteQuestionarioMockMvc;

    private RisposteQuestionario risposteQuestionario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RisposteQuestionario createEntity(EntityManager em) {
        RisposteQuestionario risposteQuestionario = new RisposteQuestionario()
            .idUtente(DEFAULT_ID_UTENTE)
            .testo(DEFAULT_TESTO);
        return risposteQuestionario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RisposteQuestionario createUpdatedEntity(EntityManager em) {
        RisposteQuestionario risposteQuestionario = new RisposteQuestionario()
            .idUtente(UPDATED_ID_UTENTE)
            .testo(UPDATED_TESTO);
        return risposteQuestionario;
    }

    @BeforeEach
    public void initTest() {
        risposteQuestionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createRisposteQuestionario() throws Exception {
        int databaseSizeBeforeCreate = risposteQuestionarioRepository.findAll().size();
        // Create the RisposteQuestionario
        RisposteQuestionarioDTO risposteQuestionarioDTO = risposteQuestionarioMapper.toDto(risposteQuestionario);
        restRisposteQuestionarioMockMvc.perform(post("/api/risposte-questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risposteQuestionarioDTO)))
            .andExpect(status().isCreated());

        // Validate the RisposteQuestionario in the database
        List<RisposteQuestionario> risposteQuestionarioList = risposteQuestionarioRepository.findAll();
        assertThat(risposteQuestionarioList).hasSize(databaseSizeBeforeCreate + 1);
        RisposteQuestionario testRisposteQuestionario = risposteQuestionarioList.get(risposteQuestionarioList.size() - 1);
        assertThat(testRisposteQuestionario.getIdUtente()).isEqualTo(DEFAULT_ID_UTENTE);
        assertThat(testRisposteQuestionario.getTesto()).isEqualTo(DEFAULT_TESTO);
    }

    @Test
    @Transactional
    public void createRisposteQuestionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = risposteQuestionarioRepository.findAll().size();

        // Create the RisposteQuestionario with an existing ID
        risposteQuestionario.setId(1L);
        RisposteQuestionarioDTO risposteQuestionarioDTO = risposteQuestionarioMapper.toDto(risposteQuestionario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRisposteQuestionarioMockMvc.perform(post("/api/risposte-questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risposteQuestionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RisposteQuestionario in the database
        List<RisposteQuestionario> risposteQuestionarioList = risposteQuestionarioRepository.findAll();
        assertThat(risposteQuestionarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRisposteQuestionarios() throws Exception {
        // Initialize the database
        risposteQuestionarioRepository.saveAndFlush(risposteQuestionario);

        // Get all the risposteQuestionarioList
        restRisposteQuestionarioMockMvc.perform(get("/api/risposte-questionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(risposteQuestionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUtente").value(hasItem(DEFAULT_ID_UTENTE.intValue())))
            .andExpect(jsonPath("$.[*].testo").value(hasItem(DEFAULT_TESTO)));
    }
    
    @Test
    @Transactional
    public void getRisposteQuestionario() throws Exception {
        // Initialize the database
        risposteQuestionarioRepository.saveAndFlush(risposteQuestionario);

        // Get the risposteQuestionario
        restRisposteQuestionarioMockMvc.perform(get("/api/risposte-questionarios/{id}", risposteQuestionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(risposteQuestionario.getId().intValue()))
            .andExpect(jsonPath("$.idUtente").value(DEFAULT_ID_UTENTE.intValue()))
            .andExpect(jsonPath("$.testo").value(DEFAULT_TESTO));
    }
    @Test
    @Transactional
    public void getNonExistingRisposteQuestionario() throws Exception {
        // Get the risposteQuestionario
        restRisposteQuestionarioMockMvc.perform(get("/api/risposte-questionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRisposteQuestionario() throws Exception {
        // Initialize the database
        risposteQuestionarioRepository.saveAndFlush(risposteQuestionario);

        int databaseSizeBeforeUpdate = risposteQuestionarioRepository.findAll().size();

        // Update the risposteQuestionario
        RisposteQuestionario updatedRisposteQuestionario = risposteQuestionarioRepository.findById(risposteQuestionario.getId()).get();
        // Disconnect from session so that the updates on updatedRisposteQuestionario are not directly saved in db
        em.detach(updatedRisposteQuestionario);
        updatedRisposteQuestionario
            .idUtente(UPDATED_ID_UTENTE)
            .testo(UPDATED_TESTO);
        RisposteQuestionarioDTO risposteQuestionarioDTO = risposteQuestionarioMapper.toDto(updatedRisposteQuestionario);

        restRisposteQuestionarioMockMvc.perform(put("/api/risposte-questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risposteQuestionarioDTO)))
            .andExpect(status().isOk());

        // Validate the RisposteQuestionario in the database
        List<RisposteQuestionario> risposteQuestionarioList = risposteQuestionarioRepository.findAll();
        assertThat(risposteQuestionarioList).hasSize(databaseSizeBeforeUpdate);
        RisposteQuestionario testRisposteQuestionario = risposteQuestionarioList.get(risposteQuestionarioList.size() - 1);
        assertThat(testRisposteQuestionario.getIdUtente()).isEqualTo(UPDATED_ID_UTENTE);
        assertThat(testRisposteQuestionario.getTesto()).isEqualTo(UPDATED_TESTO);
    }

    @Test
    @Transactional
    public void updateNonExistingRisposteQuestionario() throws Exception {
        int databaseSizeBeforeUpdate = risposteQuestionarioRepository.findAll().size();

        // Create the RisposteQuestionario
        RisposteQuestionarioDTO risposteQuestionarioDTO = risposteQuestionarioMapper.toDto(risposteQuestionario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRisposteQuestionarioMockMvc.perform(put("/api/risposte-questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(risposteQuestionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RisposteQuestionario in the database
        List<RisposteQuestionario> risposteQuestionarioList = risposteQuestionarioRepository.findAll();
        assertThat(risposteQuestionarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRisposteQuestionario() throws Exception {
        // Initialize the database
        risposteQuestionarioRepository.saveAndFlush(risposteQuestionario);

        int databaseSizeBeforeDelete = risposteQuestionarioRepository.findAll().size();

        // Delete the risposteQuestionario
        restRisposteQuestionarioMockMvc.perform(delete("/api/risposte-questionarios/{id}", risposteQuestionario.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RisposteQuestionario> risposteQuestionarioList = risposteQuestionarioRepository.findAll();
        assertThat(risposteQuestionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
