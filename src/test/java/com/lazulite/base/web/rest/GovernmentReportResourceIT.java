package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.GovernmentReport;
import com.lazulite.base.repository.GovernmentReportRepository;
import com.lazulite.base.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lazulite.base.domain.enumeration.GovernmentReportType;
/**
 * Integration tests for the {@link GovernmentReportResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class GovernmentReportResourceIT {

    private static final GovernmentReportType DEFAULT_TYPE = GovernmentReportType.Written;
    private static final GovernmentReportType UPDATED_TYPE = GovernmentReportType.Oral;

    private static final Instant DEFAULT_REPORT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REPORT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_REPORT_DATE = Instant.ofEpochMilli(-1L);

    private static final String DEFAULT_OBJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT_INFORMATION = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    @Autowired
    private GovernmentReportRepository governmentReportRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGovernmentReportMockMvc;

    private GovernmentReport governmentReport;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GovernmentReportResource governmentReportResource = new GovernmentReportResource(governmentReportRepository);
        this.restGovernmentReportMockMvc = MockMvcBuilders.standaloneSetup(governmentReportResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GovernmentReport createEntity(EntityManager em) {
        GovernmentReport governmentReport = new GovernmentReport()
            .type(DEFAULT_TYPE)
            .reportDate(DEFAULT_REPORT_DATE)
            .objectName(DEFAULT_OBJECT_NAME)
            .position(DEFAULT_POSITION)
            .contactInformation(DEFAULT_CONTACT_INFORMATION)
            .content(DEFAULT_CONTENT)
            .location(DEFAULT_LOCATION);
        return governmentReport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GovernmentReport createUpdatedEntity(EntityManager em) {
        GovernmentReport governmentReport = new GovernmentReport()
            .type(UPDATED_TYPE)
            .reportDate(UPDATED_REPORT_DATE)
            .objectName(UPDATED_OBJECT_NAME)
            .position(UPDATED_POSITION)
            .contactInformation(UPDATED_CONTACT_INFORMATION)
            .content(UPDATED_CONTENT)
            .location(UPDATED_LOCATION);
        return governmentReport;
    }

    @BeforeEach
    public void initTest() {
        governmentReport = createEntity(em);
    }

    @Test
    @Transactional
    public void createGovernmentReport() throws Exception {
        int databaseSizeBeforeCreate = governmentReportRepository.findAll().size();

        // Create the GovernmentReport
        restGovernmentReportMockMvc.perform(post("/api/government-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governmentReport)))
            .andExpect(status().isCreated());

        // Validate the GovernmentReport in the database
        List<GovernmentReport> governmentReportList = governmentReportRepository.findAll();
        assertThat(governmentReportList).hasSize(databaseSizeBeforeCreate + 1);
        GovernmentReport testGovernmentReport = governmentReportList.get(governmentReportList.size() - 1);
        assertThat(testGovernmentReport.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testGovernmentReport.getReportDate()).isEqualTo(DEFAULT_REPORT_DATE);
        assertThat(testGovernmentReport.getObjectName()).isEqualTo(DEFAULT_OBJECT_NAME);
        assertThat(testGovernmentReport.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testGovernmentReport.getContactInformation()).isEqualTo(DEFAULT_CONTACT_INFORMATION);
        assertThat(testGovernmentReport.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testGovernmentReport.getLocation()).isEqualTo(DEFAULT_LOCATION);
    }

    @Test
    @Transactional
    public void createGovernmentReportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = governmentReportRepository.findAll().size();

        // Create the GovernmentReport with an existing ID
        governmentReport.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGovernmentReportMockMvc.perform(post("/api/government-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governmentReport)))
            .andExpect(status().isBadRequest());

        // Validate the GovernmentReport in the database
        List<GovernmentReport> governmentReportList = governmentReportRepository.findAll();
        assertThat(governmentReportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGovernmentReports() throws Exception {
        // Initialize the database
        governmentReportRepository.saveAndFlush(governmentReport);

        // Get all the governmentReportList
        restGovernmentReportMockMvc.perform(get("/api/government-reports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(governmentReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].reportDate").value(hasItem(DEFAULT_REPORT_DATE.toString())))
            .andExpect(jsonPath("$.[*].objectName").value(hasItem(DEFAULT_OBJECT_NAME.toString())))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION.toString())))
            .andExpect(jsonPath("$.[*].contactInformation").value(hasItem(DEFAULT_CONTACT_INFORMATION.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION.toString())));
    }
    
    @Test
    @Transactional
    public void getGovernmentReport() throws Exception {
        // Initialize the database
        governmentReportRepository.saveAndFlush(governmentReport);

        // Get the governmentReport
        restGovernmentReportMockMvc.perform(get("/api/government-reports/{id}", governmentReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(governmentReport.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.reportDate").value(DEFAULT_REPORT_DATE.toString()))
            .andExpect(jsonPath("$.objectName").value(DEFAULT_OBJECT_NAME.toString()))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION.toString()))
            .andExpect(jsonPath("$.contactInformation").value(DEFAULT_CONTACT_INFORMATION.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGovernmentReport() throws Exception {
        // Get the governmentReport
        restGovernmentReportMockMvc.perform(get("/api/government-reports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGovernmentReport() throws Exception {
        // Initialize the database
        governmentReportRepository.saveAndFlush(governmentReport);

        int databaseSizeBeforeUpdate = governmentReportRepository.findAll().size();

        // Update the governmentReport
        GovernmentReport updatedGovernmentReport = governmentReportRepository.findById(governmentReport.getId()).get();
        // Disconnect from session so that the updates on updatedGovernmentReport are not directly saved in db
        em.detach(updatedGovernmentReport);
        updatedGovernmentReport
            .type(UPDATED_TYPE)
            .reportDate(UPDATED_REPORT_DATE)
            .objectName(UPDATED_OBJECT_NAME)
            .position(UPDATED_POSITION)
            .contactInformation(UPDATED_CONTACT_INFORMATION)
            .content(UPDATED_CONTENT)
            .location(UPDATED_LOCATION);

        restGovernmentReportMockMvc.perform(put("/api/government-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGovernmentReport)))
            .andExpect(status().isOk());

        // Validate the GovernmentReport in the database
        List<GovernmentReport> governmentReportList = governmentReportRepository.findAll();
        assertThat(governmentReportList).hasSize(databaseSizeBeforeUpdate);
        GovernmentReport testGovernmentReport = governmentReportList.get(governmentReportList.size() - 1);
        assertThat(testGovernmentReport.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testGovernmentReport.getReportDate()).isEqualTo(UPDATED_REPORT_DATE);
        assertThat(testGovernmentReport.getObjectName()).isEqualTo(UPDATED_OBJECT_NAME);
        assertThat(testGovernmentReport.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testGovernmentReport.getContactInformation()).isEqualTo(UPDATED_CONTACT_INFORMATION);
        assertThat(testGovernmentReport.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testGovernmentReport.getLocation()).isEqualTo(UPDATED_LOCATION);
    }

    @Test
    @Transactional
    public void updateNonExistingGovernmentReport() throws Exception {
        int databaseSizeBeforeUpdate = governmentReportRepository.findAll().size();

        // Create the GovernmentReport

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGovernmentReportMockMvc.perform(put("/api/government-reports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(governmentReport)))
            .andExpect(status().isBadRequest());

        // Validate the GovernmentReport in the database
        List<GovernmentReport> governmentReportList = governmentReportRepository.findAll();
        assertThat(governmentReportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGovernmentReport() throws Exception {
        // Initialize the database
        governmentReportRepository.saveAndFlush(governmentReport);

        int databaseSizeBeforeDelete = governmentReportRepository.findAll().size();

        // Delete the governmentReport
        restGovernmentReportMockMvc.perform(delete("/api/government-reports/{id}", governmentReport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GovernmentReport> governmentReportList = governmentReportRepository.findAll();
        assertThat(governmentReportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GovernmentReport.class);
        GovernmentReport governmentReport1 = new GovernmentReport();
        governmentReport1.setId(1L);
        GovernmentReport governmentReport2 = new GovernmentReport();
        governmentReport2.setId(governmentReport1.getId());
        assertThat(governmentReport1).isEqualTo(governmentReport2);
        governmentReport2.setId(2L);
        assertThat(governmentReport1).isNotEqualTo(governmentReport2);
        governmentReport1.setId(null);
        assertThat(governmentReport1).isNotEqualTo(governmentReport2);
    }
}
