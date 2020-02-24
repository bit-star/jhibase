package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.ServiceWindow;
import com.lazulite.base.repository.ServiceWindowRepository;
import com.lazulite.base.service.ServiceWindowService;
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
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lazulite.base.domain.enumeration.Status;
/**
 * Integration tests for the {@link ServiceWindowResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class ServiceWindowResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final Integer DEFAULT_RANK = 1;
    private static final Integer UPDATED_RANK = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Status DEFAULT_STATUS = Status.Available;
    private static final Status UPDATED_STATUS = Status.Unavailable;

    @Autowired
    private ServiceWindowRepository serviceWindowRepository;

    @Autowired
    private ServiceWindowService serviceWindowService;

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

    private MockMvc restServiceWindowMockMvc;

    private ServiceWindow serviceWindow;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServiceWindowResource serviceWindowResource = new ServiceWindowResource(serviceWindowService);
        this.restServiceWindowMockMvc = MockMvcBuilders.standaloneSetup(serviceWindowResource)
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
    public static ServiceWindow createEntity(EntityManager em) {
        ServiceWindow serviceWindow = new ServiceWindow()
            .name(DEFAULT_NAME)
            .userId(DEFAULT_USER_ID)
            .link(DEFAULT_LINK)
            .icon(DEFAULT_ICON)
            .rank(DEFAULT_RANK)
            .remark(DEFAULT_REMARK)
            .status(DEFAULT_STATUS);
        return serviceWindow;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceWindow createUpdatedEntity(EntityManager em) {
        ServiceWindow serviceWindow = new ServiceWindow()
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID)
            .link(UPDATED_LINK)
            .icon(UPDATED_ICON)
            .rank(UPDATED_RANK)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS);
        return serviceWindow;
    }

    @BeforeEach
    public void initTest() {
        serviceWindow = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceWindow() throws Exception {
        int databaseSizeBeforeCreate = serviceWindowRepository.findAll().size();

        // Create the ServiceWindow
        restServiceWindowMockMvc.perform(post("/api/service-windows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceWindow)))
            .andExpect(status().isCreated());

        // Validate the ServiceWindow in the database
        List<ServiceWindow> serviceWindowList = serviceWindowRepository.findAll();
        assertThat(serviceWindowList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceWindow testServiceWindow = serviceWindowList.get(serviceWindowList.size() - 1);
        assertThat(testServiceWindow.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceWindow.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testServiceWindow.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testServiceWindow.getIcon()).isEqualTo(DEFAULT_ICON);
        assertThat(testServiceWindow.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testServiceWindow.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testServiceWindow.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createServiceWindowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceWindowRepository.findAll().size();

        // Create the ServiceWindow with an existing ID
        serviceWindow.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceWindowMockMvc.perform(post("/api/service-windows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceWindow)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceWindow in the database
        List<ServiceWindow> serviceWindowList = serviceWindowRepository.findAll();
        assertThat(serviceWindowList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllServiceWindows() throws Exception {
        // Initialize the database
        serviceWindowRepository.saveAndFlush(serviceWindow);

        // Get all the serviceWindowList
        restServiceWindowMockMvc.perform(get("/api/service-windows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceWindow.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].link").value(hasItem(DEFAULT_LINK)))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getServiceWindow() throws Exception {
        // Initialize the database
        serviceWindowRepository.saveAndFlush(serviceWindow);

        // Get the serviceWindow
        restServiceWindowMockMvc.perform(get("/api/service-windows/{id}", serviceWindow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceWindow.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.link").value(DEFAULT_LINK))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServiceWindow() throws Exception {
        // Get the serviceWindow
        restServiceWindowMockMvc.perform(get("/api/service-windows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceWindow() throws Exception {
        // Initialize the database
        serviceWindowService.save(serviceWindow);

        int databaseSizeBeforeUpdate = serviceWindowRepository.findAll().size();

        // Update the serviceWindow
        ServiceWindow updatedServiceWindow = serviceWindowRepository.findById(serviceWindow.getId()).get();
        // Disconnect from session so that the updates on updatedServiceWindow are not directly saved in db
        em.detach(updatedServiceWindow);
        updatedServiceWindow
            .name(UPDATED_NAME)
            .userId(UPDATED_USER_ID)
            .link(UPDATED_LINK)
            .icon(UPDATED_ICON)
            .rank(UPDATED_RANK)
            .remark(UPDATED_REMARK)
            .status(UPDATED_STATUS);

        restServiceWindowMockMvc.perform(put("/api/service-windows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedServiceWindow)))
            .andExpect(status().isOk());

        // Validate the ServiceWindow in the database
        List<ServiceWindow> serviceWindowList = serviceWindowRepository.findAll();
        assertThat(serviceWindowList).hasSize(databaseSizeBeforeUpdate);
        ServiceWindow testServiceWindow = serviceWindowList.get(serviceWindowList.size() - 1);
        assertThat(testServiceWindow.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceWindow.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testServiceWindow.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testServiceWindow.getIcon()).isEqualTo(UPDATED_ICON);
        assertThat(testServiceWindow.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testServiceWindow.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testServiceWindow.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceWindow() throws Exception {
        int databaseSizeBeforeUpdate = serviceWindowRepository.findAll().size();

        // Create the ServiceWindow

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceWindowMockMvc.perform(put("/api/service-windows")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceWindow)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceWindow in the database
        List<ServiceWindow> serviceWindowList = serviceWindowRepository.findAll();
        assertThat(serviceWindowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceWindow() throws Exception {
        // Initialize the database
        serviceWindowService.save(serviceWindow);

        int databaseSizeBeforeDelete = serviceWindowRepository.findAll().size();

        // Delete the serviceWindow
        restServiceWindowMockMvc.perform(delete("/api/service-windows/{id}", serviceWindow.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceWindow> serviceWindowList = serviceWindowRepository.findAll();
        assertThat(serviceWindowList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
