package it.contrader.schedamicroservice.web.rest;

import it.contrader.schedamicroservice.SchedaApp;
import it.contrader.schedamicroservice.domain.Scheda;
import it.contrader.schedamicroservice.repository.SchedaRepository;
import it.contrader.schedamicroservice.service.SchedaService;
import it.contrader.schedamicroservice.service.dto.SchedaDTO;
import it.contrader.schedamicroservice.service.mapper.SchedaMapper;

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
 * Integration tests for the {@link SchedaResource} REST controller.
 */
@SpringBootTest(classes = SchedaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SchedaResourceIT {

    private static final String DEFAULT_DOMANDA = "AAAAAAAAAA";
    private static final String UPDATED_DOMANDA = "BBBBBBBBBB";

    private static final String DEFAULT_RISPOSTA_1 = "AAAAAAAAAA";
    private static final String UPDATED_RISPOSTA_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RISPOSTA_2 = "AAAAAAAAAA";
    private static final String UPDATED_RISPOSTA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RISPOSTA_3 = "AAAAAAAAAA";
    private static final String UPDATED_RISPOSTA_3 = "BBBBBBBBBB";

    @Autowired
    private SchedaRepository schedaRepository;

    @Autowired
    private SchedaMapper schedaMapper;

    @Autowired
    private SchedaService schedaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSchedaMockMvc;

    private Scheda scheda;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scheda createEntity(EntityManager em) {
        Scheda scheda = new Scheda()
            .domanda(DEFAULT_DOMANDA)
            .risposta1(DEFAULT_RISPOSTA_1)
            .risposta2(DEFAULT_RISPOSTA_2)
            .risposta3(DEFAULT_RISPOSTA_3);
        return scheda;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scheda createUpdatedEntity(EntityManager em) {
        Scheda scheda = new Scheda()
            .domanda(UPDATED_DOMANDA)
            .risposta1(UPDATED_RISPOSTA_1)
            .risposta2(UPDATED_RISPOSTA_2)
            .risposta3(UPDATED_RISPOSTA_3);
        return scheda;
    }

    @BeforeEach
    public void initTest() {
        scheda = createEntity(em);
    }

    @Test
    @Transactional
    public void createScheda() throws Exception {
        int databaseSizeBeforeCreate = schedaRepository.findAll().size();
        // Create the Scheda
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);
        restSchedaMockMvc.perform(post("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isCreated());

        // Validate the Scheda in the database
        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeCreate + 1);
        Scheda testScheda = schedaList.get(schedaList.size() - 1);
        assertThat(testScheda.getDomanda()).isEqualTo(DEFAULT_DOMANDA);
        assertThat(testScheda.getRisposta1()).isEqualTo(DEFAULT_RISPOSTA_1);
        assertThat(testScheda.getRisposta2()).isEqualTo(DEFAULT_RISPOSTA_2);
        assertThat(testScheda.getRisposta3()).isEqualTo(DEFAULT_RISPOSTA_3);
    }

    @Test
    @Transactional
    public void createSchedaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = schedaRepository.findAll().size();

        // Create the Scheda with an existing ID
        scheda.setId(1L);
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSchedaMockMvc.perform(post("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheda in the database
        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDomandaIsRequired() throws Exception {
        int databaseSizeBeforeTest = schedaRepository.findAll().size();
        // set the field null
        scheda.setDomanda(null);

        // Create the Scheda, which fails.
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);


        restSchedaMockMvc.perform(post("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isBadRequest());

        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRisposta1IsRequired() throws Exception {
        int databaseSizeBeforeTest = schedaRepository.findAll().size();
        // set the field null
        scheda.setRisposta1(null);

        // Create the Scheda, which fails.
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);


        restSchedaMockMvc.perform(post("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isBadRequest());

        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRisposta2IsRequired() throws Exception {
        int databaseSizeBeforeTest = schedaRepository.findAll().size();
        // set the field null
        scheda.setRisposta2(null);

        // Create the Scheda, which fails.
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);


        restSchedaMockMvc.perform(post("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isBadRequest());

        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSchedas() throws Exception {
        // Initialize the database
        schedaRepository.saveAndFlush(scheda);

        // Get all the schedaList
        restSchedaMockMvc.perform(get("/api/schedas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scheda.getId().intValue())))
            .andExpect(jsonPath("$.[*].domanda").value(hasItem(DEFAULT_DOMANDA)))
            .andExpect(jsonPath("$.[*].risposta1").value(hasItem(DEFAULT_RISPOSTA_1)))
            .andExpect(jsonPath("$.[*].risposta2").value(hasItem(DEFAULT_RISPOSTA_2)))
            .andExpect(jsonPath("$.[*].risposta3").value(hasItem(DEFAULT_RISPOSTA_3)));
    }
    
    @Test
    @Transactional
    public void getScheda() throws Exception {
        // Initialize the database
        schedaRepository.saveAndFlush(scheda);

        // Get the scheda
        restSchedaMockMvc.perform(get("/api/schedas/{id}", scheda.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scheda.getId().intValue()))
            .andExpect(jsonPath("$.domanda").value(DEFAULT_DOMANDA))
            .andExpect(jsonPath("$.risposta1").value(DEFAULT_RISPOSTA_1))
            .andExpect(jsonPath("$.risposta2").value(DEFAULT_RISPOSTA_2))
            .andExpect(jsonPath("$.risposta3").value(DEFAULT_RISPOSTA_3));
    }
    @Test
    @Transactional
    public void getNonExistingScheda() throws Exception {
        // Get the scheda
        restSchedaMockMvc.perform(get("/api/schedas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateScheda() throws Exception {
        // Initialize the database
        schedaRepository.saveAndFlush(scheda);

        int databaseSizeBeforeUpdate = schedaRepository.findAll().size();

        // Update the scheda
        Scheda updatedScheda = schedaRepository.findById(scheda.getId()).get();
        // Disconnect from session so that the updates on updatedScheda are not directly saved in db
        em.detach(updatedScheda);
        updatedScheda
            .domanda(UPDATED_DOMANDA)
            .risposta1(UPDATED_RISPOSTA_1)
            .risposta2(UPDATED_RISPOSTA_2)
            .risposta3(UPDATED_RISPOSTA_3);
        SchedaDTO schedaDTO = schedaMapper.toDto(updatedScheda);

        restSchedaMockMvc.perform(put("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isOk());

        // Validate the Scheda in the database
        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeUpdate);
        Scheda testScheda = schedaList.get(schedaList.size() - 1);
        assertThat(testScheda.getDomanda()).isEqualTo(UPDATED_DOMANDA);
        assertThat(testScheda.getRisposta1()).isEqualTo(UPDATED_RISPOSTA_1);
        assertThat(testScheda.getRisposta2()).isEqualTo(UPDATED_RISPOSTA_2);
        assertThat(testScheda.getRisposta3()).isEqualTo(UPDATED_RISPOSTA_3);
    }

    @Test
    @Transactional
    public void updateNonExistingScheda() throws Exception {
        int databaseSizeBeforeUpdate = schedaRepository.findAll().size();

        // Create the Scheda
        SchedaDTO schedaDTO = schedaMapper.toDto(scheda);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSchedaMockMvc.perform(put("/api/schedas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(schedaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Scheda in the database
        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteScheda() throws Exception {
        // Initialize the database
        schedaRepository.saveAndFlush(scheda);

        int databaseSizeBeforeDelete = schedaRepository.findAll().size();

        // Delete the scheda
        restSchedaMockMvc.perform(delete("/api/schedas/{id}", scheda.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Scheda> schedaList = schedaRepository.findAll();
        assertThat(schedaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
