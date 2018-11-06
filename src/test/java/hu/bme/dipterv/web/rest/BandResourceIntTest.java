package hu.bme.dipterv.web.rest;

import hu.bme.dipterv.JhtestApp;

import hu.bme.dipterv.domain.Band;
import hu.bme.dipterv.repository.BandRepository;
import hu.bme.dipterv.service.BandService;
import hu.bme.dipterv.service.dto.BandDTO;
import hu.bme.dipterv.service.mapper.BandMapper;
import hu.bme.dipterv.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;


import static hu.bme.dipterv.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BandResource REST controller.
 *
 * @see BandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhtestApp.class)
public class BandResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FOUNDED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FOUNDED = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BandRepository bandRepository;

    @Mock
    private BandRepository bandRepositoryMock;

    @Autowired
    private BandMapper bandMapper;
    

    @Mock
    private BandService bandServiceMock;

    @Autowired
    private BandService bandService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBandMockMvc;

    private Band band;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BandResource bandResource = new BandResource(bandService);
        this.restBandMockMvc = MockMvcBuilders.standaloneSetup(bandResource)
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
    public static Band createEntity(EntityManager em) {
        Band band = new Band()
            .name(DEFAULT_NAME)
            .founded(DEFAULT_FOUNDED);
        return band;
    }

    @Before
    public void initTest() {
        band = createEntity(em);
    }

    @Test
    @Transactional
    public void createBand() throws Exception {
        int databaseSizeBeforeCreate = bandRepository.findAll().size();

        // Create the Band
        BandDTO bandDTO = bandMapper.toDto(band);
        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandDTO)))
            .andExpect(status().isCreated());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeCreate + 1);
        Band testBand = bandList.get(bandList.size() - 1);
        assertThat(testBand.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBand.getFounded()).isEqualTo(DEFAULT_FOUNDED);
    }

    @Test
    @Transactional
    public void createBandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bandRepository.findAll().size();

        // Create the Band with an existing ID
        band.setId(1L);
        BandDTO bandDTO = bandMapper.toDto(band);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBandMockMvc.perform(post("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBands() throws Exception {
        // Initialize the database
        bandRepository.saveAndFlush(band);

        // Get all the bandList
        restBandMockMvc.perform(get("/api/bands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(band.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].founded").value(hasItem(DEFAULT_FOUNDED.toString())));
    }
    
    public void getAllBandsWithEagerRelationshipsIsEnabled() throws Exception {
        BandResource bandResource = new BandResource(bandServiceMock);
        when(bandServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restBandMockMvc = MockMvcBuilders.standaloneSetup(bandResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBandMockMvc.perform(get("/api/bands?eagerload=true"))
        .andExpect(status().isOk());

        verify(bandServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllBandsWithEagerRelationshipsIsNotEnabled() throws Exception {
        BandResource bandResource = new BandResource(bandServiceMock);
            when(bandServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restBandMockMvc = MockMvcBuilders.standaloneSetup(bandResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBandMockMvc.perform(get("/api/bands?eagerload=true"))
        .andExpect(status().isOk());

            verify(bandServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getBand() throws Exception {
        // Initialize the database
        bandRepository.saveAndFlush(band);

        // Get the band
        restBandMockMvc.perform(get("/api/bands/{id}", band.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(band.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.founded").value(DEFAULT_FOUNDED.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBand() throws Exception {
        // Get the band
        restBandMockMvc.perform(get("/api/bands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBand() throws Exception {
        // Initialize the database
        bandRepository.saveAndFlush(band);

        int databaseSizeBeforeUpdate = bandRepository.findAll().size();

        // Update the band
        Band updatedBand = bandRepository.findById(band.getId()).get();
        // Disconnect from session so that the updates on updatedBand are not directly saved in db
        em.detach(updatedBand);
        updatedBand
            .name(UPDATED_NAME)
            .founded(UPDATED_FOUNDED);
        BandDTO bandDTO = bandMapper.toDto(updatedBand);

        restBandMockMvc.perform(put("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandDTO)))
            .andExpect(status().isOk());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeUpdate);
        Band testBand = bandList.get(bandList.size() - 1);
        assertThat(testBand.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBand.getFounded()).isEqualTo(UPDATED_FOUNDED);
    }

    @Test
    @Transactional
    public void updateNonExistingBand() throws Exception {
        int databaseSizeBeforeUpdate = bandRepository.findAll().size();

        // Create the Band
        BandDTO bandDTO = bandMapper.toDto(band);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBandMockMvc.perform(put("/api/bands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Band in the database
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBand() throws Exception {
        // Initialize the database
        bandRepository.saveAndFlush(band);

        int databaseSizeBeforeDelete = bandRepository.findAll().size();

        // Get the band
        restBandMockMvc.perform(delete("/api/bands/{id}", band.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Band> bandList = bandRepository.findAll();
        assertThat(bandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Band.class);
        Band band1 = new Band();
        band1.setId(1L);
        Band band2 = new Band();
        band2.setId(band1.getId());
        assertThat(band1).isEqualTo(band2);
        band2.setId(2L);
        assertThat(band1).isNotEqualTo(band2);
        band1.setId(null);
        assertThat(band1).isNotEqualTo(band2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BandDTO.class);
        BandDTO bandDTO1 = new BandDTO();
        bandDTO1.setId(1L);
        BandDTO bandDTO2 = new BandDTO();
        assertThat(bandDTO1).isNotEqualTo(bandDTO2);
        bandDTO2.setId(bandDTO1.getId());
        assertThat(bandDTO1).isEqualTo(bandDTO2);
        bandDTO2.setId(2L);
        assertThat(bandDTO1).isNotEqualTo(bandDTO2);
        bandDTO1.setId(null);
        assertThat(bandDTO1).isNotEqualTo(bandDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bandMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bandMapper.fromId(null)).isNull();
    }
}
