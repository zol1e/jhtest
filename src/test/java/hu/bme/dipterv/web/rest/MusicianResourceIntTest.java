package hu.bme.dipterv.web.rest;

import hu.bme.dipterv.JhtestApp;

import hu.bme.dipterv.domain.Musician;
import hu.bme.dipterv.repository.MusicianRepository;
import hu.bme.dipterv.service.MusicianService;
import hu.bme.dipterv.service.dto.MusicianDTO;
import hu.bme.dipterv.service.mapper.MusicianMapper;
import hu.bme.dipterv.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static hu.bme.dipterv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the MusicianResource REST controller.
 *
 * @see MusicianResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhtestApp.class)
public class MusicianResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTHDAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTHDAY = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private MusicianRepository musicianRepository;

    @Autowired
    private MusicianMapper musicianMapper;
    
    @Autowired
    private MusicianService musicianService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMusicianMockMvc;

    private Musician musician;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MusicianResource musicianResource = new MusicianResource(musicianService);
        this.restMusicianMockMvc = MockMvcBuilders.standaloneSetup(musicianResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Musician createEntity(EntityManager em) {
        Musician musician = new Musician()
            .name(DEFAULT_NAME)
            .birthday(DEFAULT_BIRTHDAY);
        return musician;
    }

    @Before
    public void initTest() {
        musician = createEntity(em);
    }

    @Test
    @Transactional
    public void createMusician() throws Exception {
        int databaseSizeBeforeCreate = musicianRepository.findAll().size();

        // Create the Musician
        MusicianDTO musicianDTO = musicianMapper.toDto(musician);
        restMusicianMockMvc.perform(post("/api/musicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(musicianDTO)))
            .andExpect(status().isCreated());

        // Validate the Musician in the database
        List<Musician> musicianList = musicianRepository.findAll();
        assertThat(musicianList).hasSize(databaseSizeBeforeCreate + 1);
        Musician testMusician = musicianList.get(musicianList.size() - 1);
        assertThat(testMusician.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMusician.getBirthday()).isEqualTo(DEFAULT_BIRTHDAY);
    }

    @Test
    @Transactional
    public void createMusicianWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = musicianRepository.findAll().size();

        // Create the Musician with an existing ID
        musician.setId(1L);
        MusicianDTO musicianDTO = musicianMapper.toDto(musician);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMusicianMockMvc.perform(post("/api/musicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(musicianDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Musician in the database
        List<Musician> musicianList = musicianRepository.findAll();
        assertThat(musicianList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMusicians() throws Exception {
        // Initialize the database
        musicianRepository.saveAndFlush(musician);

        // Get all the musicianList
        restMusicianMockMvc.perform(get("/api/musicians?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(musician.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].birthday").value(hasItem(DEFAULT_BIRTHDAY.toString())));
    }
    
    @Test
    @Transactional
    public void getMusician() throws Exception {
        // Initialize the database
        musicianRepository.saveAndFlush(musician);

        // Get the musician
        restMusicianMockMvc.perform(get("/api/musicians/{id}", musician.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(musician.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.birthday").value(DEFAULT_BIRTHDAY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMusician() throws Exception {
        // Get the musician
        restMusicianMockMvc.perform(get("/api/musicians/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMusician() throws Exception {
        // Initialize the database
        musicianRepository.saveAndFlush(musician);

        int databaseSizeBeforeUpdate = musicianRepository.findAll().size();

        // Update the musician
        Musician updatedMusician = musicianRepository.findById(musician.getId()).get();
        // Disconnect from session so that the updates on updatedMusician are not directly saved in db
        em.detach(updatedMusician);
        updatedMusician
            .name(UPDATED_NAME)
            .birthday(UPDATED_BIRTHDAY);
        MusicianDTO musicianDTO = musicianMapper.toDto(updatedMusician);

        restMusicianMockMvc.perform(put("/api/musicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(musicianDTO)))
            .andExpect(status().isOk());

        // Validate the Musician in the database
        List<Musician> musicianList = musicianRepository.findAll();
        assertThat(musicianList).hasSize(databaseSizeBeforeUpdate);
        Musician testMusician = musicianList.get(musicianList.size() - 1);
        assertThat(testMusician.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMusician.getBirthday()).isEqualTo(UPDATED_BIRTHDAY);
    }

    @Test
    @Transactional
    public void updateNonExistingMusician() throws Exception {
        int databaseSizeBeforeUpdate = musicianRepository.findAll().size();

        // Create the Musician
        MusicianDTO musicianDTO = musicianMapper.toDto(musician);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMusicianMockMvc.perform(put("/api/musicians")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(musicianDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Musician in the database
        List<Musician> musicianList = musicianRepository.findAll();
        assertThat(musicianList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMusician() throws Exception {
        // Initialize the database
        musicianRepository.saveAndFlush(musician);

        int databaseSizeBeforeDelete = musicianRepository.findAll().size();

        // Get the musician
        restMusicianMockMvc.perform(delete("/api/musicians/{id}", musician.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Musician> musicianList = musicianRepository.findAll();
        assertThat(musicianList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Musician.class);
        Musician musician1 = new Musician();
        musician1.setId(1L);
        Musician musician2 = new Musician();
        musician2.setId(musician1.getId());
        assertThat(musician1).isEqualTo(musician2);
        musician2.setId(2L);
        assertThat(musician1).isNotEqualTo(musician2);
        musician1.setId(null);
        assertThat(musician1).isNotEqualTo(musician2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MusicianDTO.class);
        MusicianDTO musicianDTO1 = new MusicianDTO();
        musicianDTO1.setId(1L);
        MusicianDTO musicianDTO2 = new MusicianDTO();
        assertThat(musicianDTO1).isNotEqualTo(musicianDTO2);
        musicianDTO2.setId(musicianDTO1.getId());
        assertThat(musicianDTO1).isEqualTo(musicianDTO2);
        musicianDTO2.setId(2L);
        assertThat(musicianDTO1).isNotEqualTo(musicianDTO2);
        musicianDTO1.setId(null);
        assertThat(musicianDTO1).isNotEqualTo(musicianDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(musicianMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(musicianMapper.fromId(null)).isNull();
    }
}
