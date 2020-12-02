package com.contrader.web.rest;

import com.contrader.QuestionarioApp;
import com.contrader.domain.Questionario;
import com.contrader.repository.QuestionarioRepository;
import com.contrader.service.QuestionarioService;
import com.contrader.service.dto.QuestionarioDTO;
import com.contrader.service.mapper.QuestionarioMapper;

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
 * Integration tests for the {@link QuestionarioResource} REST controller.
 */
@SpringBootTest(classes = QuestionarioApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class QuestionarioResourceIT {

    private static final Integer DEFAULT_INDEX_GRADE = 1;
    private static final Integer UPDATED_INDEX_GRADE = 2;

    private static final String DEFAULT_COMMENTI = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTI = "BBBBBBBBBB";

    @Autowired
    private QuestionarioRepository questionarioRepository;

    @Autowired
    private QuestionarioMapper questionarioMapper;

    @Autowired
    private QuestionarioService questionarioService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionarioMockMvc;

    private Questionario questionario;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questionario createEntity(EntityManager em) {
        Questionario questionario = new Questionario()
            .indexGrade(DEFAULT_INDEX_GRADE)
            .commenti(DEFAULT_COMMENTI);
        return questionario;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Questionario createUpdatedEntity(EntityManager em) {
        Questionario questionario = new Questionario()
            .indexGrade(UPDATED_INDEX_GRADE)
            .commenti(UPDATED_COMMENTI);
        return questionario;
    }

    @BeforeEach
    public void initTest() {
        questionario = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestionario() throws Exception {
        int databaseSizeBeforeCreate = questionarioRepository.findAll().size();
        // Create the Questionario
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);
        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isCreated());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeCreate + 1);
        Questionario testQuestionario = questionarioList.get(questionarioList.size() - 1);
        assertThat(testQuestionario.getIndexGrade()).isEqualTo(DEFAULT_INDEX_GRADE);
        assertThat(testQuestionario.getCommenti()).isEqualTo(DEFAULT_COMMENTI);
    }

    @Test
    @Transactional
    public void createQuestionarioWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionarioRepository.findAll().size();

        // Create the Questionario with an existing ID
        questionario.setId(1L);
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionarioMockMvc.perform(post("/api/questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuestionarios() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        // Get all the questionarioList
        restQuestionarioMockMvc.perform(get("/api/questionarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(questionario.getId().intValue())))
            .andExpect(jsonPath("$.[*].indexGrade").value(hasItem(DEFAULT_INDEX_GRADE)))
            .andExpect(jsonPath("$.[*].commenti").value(hasItem(DEFAULT_COMMENTI)));
    }
    
    @Test
    @Transactional
    public void getQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        // Get the questionario
        restQuestionarioMockMvc.perform(get("/api/questionarios/{id}", questionario.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(questionario.getId().intValue()))
            .andExpect(jsonPath("$.indexGrade").value(DEFAULT_INDEX_GRADE))
            .andExpect(jsonPath("$.commenti").value(DEFAULT_COMMENTI));
    }
    @Test
    @Transactional
    public void getNonExistingQuestionario() throws Exception {
        // Get the questionario
        restQuestionarioMockMvc.perform(get("/api/questionarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        int databaseSizeBeforeUpdate = questionarioRepository.findAll().size();

        // Update the questionario
        Questionario updatedQuestionario = questionarioRepository.findById(questionario.getId()).get();
        // Disconnect from session so that the updates on updatedQuestionario are not directly saved in db
        em.detach(updatedQuestionario);
        updatedQuestionario
            .indexGrade(UPDATED_INDEX_GRADE)
            .commenti(UPDATED_COMMENTI);
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(updatedQuestionario);

        restQuestionarioMockMvc.perform(put("/api/questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isOk());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeUpdate);
        Questionario testQuestionario = questionarioList.get(questionarioList.size() - 1);
        assertThat(testQuestionario.getIndexGrade()).isEqualTo(UPDATED_INDEX_GRADE);
        assertThat(testQuestionario.getCommenti()).isEqualTo(UPDATED_COMMENTI);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestionario() throws Exception {
        int databaseSizeBeforeUpdate = questionarioRepository.findAll().size();

        // Create the Questionario
        QuestionarioDTO questionarioDTO = questionarioMapper.toDto(questionario);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionarioMockMvc.perform(put("/api/questionarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionarioDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Questionario in the database
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestionario() throws Exception {
        // Initialize the database
        questionarioRepository.saveAndFlush(questionario);

        int databaseSizeBeforeDelete = questionarioRepository.findAll().size();

        // Delete the questionario
        restQuestionarioMockMvc.perform(delete("/api/questionarios/{id}", questionario.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Questionario> questionarioList = questionarioRepository.findAll();
        assertThat(questionarioList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
