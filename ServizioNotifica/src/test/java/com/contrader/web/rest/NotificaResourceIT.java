package com.contrader.web.rest;

import com.contrader.ServizioNotificaApp;
import com.contrader.domain.Notifica;
import com.contrader.repository.NotificaRepository;
import com.contrader.service.NotificaService;
import com.contrader.service.dto.NotificaDTO;
import com.contrader.service.mapper.NotificaMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NotificaResource} REST controller.
 */
@SpringBootTest(classes = ServizioNotificaApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class NotificaResourceIT {

    private static final String DEFAULT_MESSAGGIO = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGGIO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private NotificaRepository notificaRepository;

    @Autowired
    private NotificaMapper notificaMapper;

    @Autowired
    private NotificaService notificaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNotificaMockMvc;

    private Notifica notifica;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Notifica createEntity(EntityManager em) {
        Notifica notifica = new Notifica()
            .messaggio(DEFAULT_MESSAGGIO)
            .data(DEFAULT_DATA);
        return notifica;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Notifica createUpdatedEntity(EntityManager em) {
        Notifica notifica = new Notifica()
            .messaggio(UPDATED_MESSAGGIO)
            .data(UPDATED_DATA);
        return notifica;
    }

    @BeforeEach
    public void initTest() {
        notifica = createEntity(em);
    }

    @Test
    @Transactional
    public void createNotifica() throws Exception {
        int databaseSizeBeforeCreate = notificaRepository.findAll().size();
        // Create the Notifica
        NotificaDTO notificaDTO = notificaMapper.toDto(notifica);
        restNotificaMockMvc.perform(post("/api/notificas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificaDTO)))
            .andExpect(status().isCreated());

        // Validate the Notifica in the database
        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeCreate + 1);
        Notifica testNotifica = notificaList.get(notificaList.size() - 1);
        assertThat(testNotifica.getMessaggio()).isEqualTo(DEFAULT_MESSAGGIO);
        assertThat(testNotifica.getData()).isEqualTo(DEFAULT_DATA);
    }

    @Test
    @Transactional
    public void createNotificaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = notificaRepository.findAll().size();

        // Create the Notifica with an existing ID
        notifica.setId(1L);
        NotificaDTO notificaDTO = notificaMapper.toDto(notifica);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNotificaMockMvc.perform(post("/api/notificas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifica in the database
        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMessaggioIsRequired() throws Exception {
        int databaseSizeBeforeTest = notificaRepository.findAll().size();
        // set the field null
        notifica.setMessaggio(null);

        // Create the Notifica, which fails.
        NotificaDTO notificaDTO = notificaMapper.toDto(notifica);


        restNotificaMockMvc.perform(post("/api/notificas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificaDTO)))
            .andExpect(status().isBadRequest());

        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNotificas() throws Exception {
        // Initialize the database
        notificaRepository.saveAndFlush(notifica);

        // Get all the notificaList
        restNotificaMockMvc.perform(get("/api/notificas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(notifica.getId().intValue())))
            .andExpect(jsonPath("$.[*].messaggio").value(hasItem(DEFAULT_MESSAGGIO)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())));
    }
    
    @Test
    @Transactional
    public void getNotifica() throws Exception {
        // Initialize the database
        notificaRepository.saveAndFlush(notifica);

        // Get the notifica
        restNotificaMockMvc.perform(get("/api/notificas/{id}", notifica.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(notifica.getId().intValue()))
            .andExpect(jsonPath("$.messaggio").value(DEFAULT_MESSAGGIO))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingNotifica() throws Exception {
        // Get the notifica
        restNotificaMockMvc.perform(get("/api/notificas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNotifica() throws Exception {
        // Initialize the database
        notificaRepository.saveAndFlush(notifica);

        int databaseSizeBeforeUpdate = notificaRepository.findAll().size();

        // Update the notifica
        Notifica updatedNotifica = notificaRepository.findById(notifica.getId()).get();
        // Disconnect from session so that the updates on updatedNotifica are not directly saved in db
        em.detach(updatedNotifica);
        updatedNotifica
            .messaggio(UPDATED_MESSAGGIO)
            .data(UPDATED_DATA);
        NotificaDTO notificaDTO = notificaMapper.toDto(updatedNotifica);

        restNotificaMockMvc.perform(put("/api/notificas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificaDTO)))
            .andExpect(status().isOk());

        // Validate the Notifica in the database
        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeUpdate);
        Notifica testNotifica = notificaList.get(notificaList.size() - 1);
        assertThat(testNotifica.getMessaggio()).isEqualTo(UPDATED_MESSAGGIO);
        assertThat(testNotifica.getData()).isEqualTo(UPDATED_DATA);
    }

    @Test
    @Transactional
    public void updateNonExistingNotifica() throws Exception {
        int databaseSizeBeforeUpdate = notificaRepository.findAll().size();

        // Create the Notifica
        NotificaDTO notificaDTO = notificaMapper.toDto(notifica);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNotificaMockMvc.perform(put("/api/notificas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(notificaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Notifica in the database
        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNotifica() throws Exception {
        // Initialize the database
        notificaRepository.saveAndFlush(notifica);

        int databaseSizeBeforeDelete = notificaRepository.findAll().size();

        // Delete the notifica
        restNotificaMockMvc.perform(delete("/api/notificas/{id}", notifica.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Notifica> notificaList = notificaRepository.findAll();
        assertThat(notificaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
