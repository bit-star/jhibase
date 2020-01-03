package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.LocationVM;
import com.lazulite.base.repository.LocationVMRepository;
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
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lazulite.base.domain.enumeration.WorkLogType;
/**
 * Integration tests for the {@link LocationVMResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class LocationVMResourceIT {

    private static final Long DEFAULT_STATION_ID = 1L;
    private static final Long UPDATED_STATION_ID = 2L;

    private static final String DEFAULT_GROUP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_STATION_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_LONGITUDE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LONGITUDE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LATITUDE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LATITUDE = new BigDecimal(2);

    private static final String DEFAULT_ACCURACY = "AAAAAAAAAA";
    private static final String UPDATED_ACCURACY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_ROAD = "AAAAAAAAAA";
    private static final String UPDATED_ROAD = "BBBBBBBBBB";

    private static final String DEFAULT_NET_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_NET_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATOR_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_OPERATOR_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DD_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DD_USER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DD_USER_ID = 1L;
    private static final Long UPDATED_DD_USER_ID = 2L;

    private static final String DEFAULT_DD_USER_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_DD_USER_PHONE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_FINISH = false;
    private static final Boolean UPDATED_IS_FINISH = true;

    private static final Long DEFAULT_WORK_LOG_MAIN_ID = 1L;
    private static final Long UPDATED_WORK_LOG_MAIN_ID = 2L;

    private static final WorkLogType DEFAULT_WORK_LOG_TYPE = WorkLogType.HealthRoom;
    private static final WorkLogType UPDATED_WORK_LOG_TYPE = WorkLogType.HealthCenter;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LocationVMRepository locationVMRepository;

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

    private MockMvc restLocationVMMockMvc;

    private LocationVM locationVM;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationVMResource locationVMResource = new LocationVMResource(locationVMRepository);
        this.restLocationVMMockMvc = MockMvcBuilders.standaloneSetup(locationVMResource)
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
    public static LocationVM createEntity(EntityManager em) {
        LocationVM locationVM = new LocationVM()
            .stationId(DEFAULT_STATION_ID)
            .groupName(DEFAULT_GROUP_NAME)
            .stationName(DEFAULT_STATION_NAME)
            .longitude(DEFAULT_LONGITUDE)
            .latitude(DEFAULT_LATITUDE)
            .accuracy(DEFAULT_ACCURACY)
            .address(DEFAULT_ADDRESS)
            .province(DEFAULT_PROVINCE)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .road(DEFAULT_ROAD)
            .netType(DEFAULT_NET_TYPE)
            .operatorType(DEFAULT_OPERATOR_TYPE)
            .ddUserName(DEFAULT_DD_USER_NAME)
            .ddUserId(DEFAULT_DD_USER_ID)
            .ddUserPhone(DEFAULT_DD_USER_PHONE)
            .isFinish(DEFAULT_IS_FINISH)
            .workLogMainId(DEFAULT_WORK_LOG_MAIN_ID)
            .workLogType(DEFAULT_WORK_LOG_TYPE)
            .createdDate(DEFAULT_CREATED_DATE);
        return locationVM;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocationVM createUpdatedEntity(EntityManager em) {
        LocationVM locationVM = new LocationVM()
            .stationId(UPDATED_STATION_ID)
            .groupName(UPDATED_GROUP_NAME)
            .stationName(UPDATED_STATION_NAME)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .accuracy(UPDATED_ACCURACY)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .road(UPDATED_ROAD)
            .netType(UPDATED_NET_TYPE)
            .operatorType(UPDATED_OPERATOR_TYPE)
            .ddUserName(UPDATED_DD_USER_NAME)
            .ddUserId(UPDATED_DD_USER_ID)
            .ddUserPhone(UPDATED_DD_USER_PHONE)
            .isFinish(UPDATED_IS_FINISH)
            .workLogMainId(UPDATED_WORK_LOG_MAIN_ID)
            .workLogType(UPDATED_WORK_LOG_TYPE)
            .createdDate(UPDATED_CREATED_DATE);
        return locationVM;
    }

    @BeforeEach
    public void initTest() {
        locationVM = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocationVM() throws Exception {
        int databaseSizeBeforeCreate = locationVMRepository.findAll().size();

        // Create the LocationVM
        restLocationVMMockMvc.perform(post("/api/location-vms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationVM)))
            .andExpect(status().isCreated());

        // Validate the LocationVM in the database
        List<LocationVM> locationVMList = locationVMRepository.findAll();
        assertThat(locationVMList).hasSize(databaseSizeBeforeCreate + 1);
        LocationVM testLocationVM = locationVMList.get(locationVMList.size() - 1);
        assertThat(testLocationVM.getStationId()).isEqualTo(DEFAULT_STATION_ID);
        assertThat(testLocationVM.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testLocationVM.getStationName()).isEqualTo(DEFAULT_STATION_NAME);
        assertThat(testLocationVM.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testLocationVM.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLocationVM.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testLocationVM.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testLocationVM.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testLocationVM.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocationVM.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testLocationVM.getRoad()).isEqualTo(DEFAULT_ROAD);
        assertThat(testLocationVM.getNetType()).isEqualTo(DEFAULT_NET_TYPE);
        assertThat(testLocationVM.getOperatorType()).isEqualTo(DEFAULT_OPERATOR_TYPE);
        assertThat(testLocationVM.getDdUserName()).isEqualTo(DEFAULT_DD_USER_NAME);
        assertThat(testLocationVM.getDdUserId()).isEqualTo(DEFAULT_DD_USER_ID);
        assertThat(testLocationVM.getDdUserPhone()).isEqualTo(DEFAULT_DD_USER_PHONE);
        assertThat(testLocationVM.isIsFinish()).isEqualTo(DEFAULT_IS_FINISH);
        assertThat(testLocationVM.getWorkLogMainId()).isEqualTo(DEFAULT_WORK_LOG_MAIN_ID);
        assertThat(testLocationVM.getWorkLogType()).isEqualTo(DEFAULT_WORK_LOG_TYPE);
        assertThat(testLocationVM.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createLocationVMWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationVMRepository.findAll().size();

        // Create the LocationVM with an existing ID
        locationVM.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationVMMockMvc.perform(post("/api/location-vms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationVM)))
            .andExpect(status().isBadRequest());

        // Validate the LocationVM in the database
        List<LocationVM> locationVMList = locationVMRepository.findAll();
        assertThat(locationVMList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocationVMS() throws Exception {
        // Initialize the database
        locationVMRepository.saveAndFlush(locationVM);

        // Get all the locationVMList
        restLocationVMMockMvc.perform(get("/api/location-vms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationVM.getId().intValue())))
            .andExpect(jsonPath("$.[*].stationId").value(hasItem(DEFAULT_STATION_ID.intValue())))
            .andExpect(jsonPath("$.[*].groupName").value(hasItem(DEFAULT_GROUP_NAME)))
            .andExpect(jsonPath("$.[*].stationName").value(hasItem(DEFAULT_STATION_NAME)))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.intValue())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.intValue())))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].province").value(hasItem(DEFAULT_PROVINCE)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].road").value(hasItem(DEFAULT_ROAD)))
            .andExpect(jsonPath("$.[*].netType").value(hasItem(DEFAULT_NET_TYPE)))
            .andExpect(jsonPath("$.[*].operatorType").value(hasItem(DEFAULT_OPERATOR_TYPE)))
            .andExpect(jsonPath("$.[*].ddUserName").value(hasItem(DEFAULT_DD_USER_NAME)))
            .andExpect(jsonPath("$.[*].ddUserId").value(hasItem(DEFAULT_DD_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].ddUserPhone").value(hasItem(DEFAULT_DD_USER_PHONE)))
            .andExpect(jsonPath("$.[*].isFinish").value(hasItem(DEFAULT_IS_FINISH.booleanValue())))
            .andExpect(jsonPath("$.[*].workLogMainId").value(hasItem(DEFAULT_WORK_LOG_MAIN_ID.intValue())))
            .andExpect(jsonPath("$.[*].workLogType").value(hasItem(DEFAULT_WORK_LOG_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getLocationVM() throws Exception {
        // Initialize the database
        locationVMRepository.saveAndFlush(locationVM);

        // Get the locationVM
        restLocationVMMockMvc.perform(get("/api/location-vms/{id}", locationVM.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locationVM.getId().intValue()))
            .andExpect(jsonPath("$.stationId").value(DEFAULT_STATION_ID.intValue()))
            .andExpect(jsonPath("$.groupName").value(DEFAULT_GROUP_NAME))
            .andExpect(jsonPath("$.stationName").value(DEFAULT_STATION_NAME))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.intValue()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.intValue()))
            .andExpect(jsonPath("$.accuracy").value(DEFAULT_ACCURACY))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.province").value(DEFAULT_PROVINCE))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.road").value(DEFAULT_ROAD))
            .andExpect(jsonPath("$.netType").value(DEFAULT_NET_TYPE))
            .andExpect(jsonPath("$.operatorType").value(DEFAULT_OPERATOR_TYPE))
            .andExpect(jsonPath("$.ddUserName").value(DEFAULT_DD_USER_NAME))
            .andExpect(jsonPath("$.ddUserId").value(DEFAULT_DD_USER_ID.intValue()))
            .andExpect(jsonPath("$.ddUserPhone").value(DEFAULT_DD_USER_PHONE))
            .andExpect(jsonPath("$.isFinish").value(DEFAULT_IS_FINISH.booleanValue()))
            .andExpect(jsonPath("$.workLogMainId").value(DEFAULT_WORK_LOG_MAIN_ID.intValue()))
            .andExpect(jsonPath("$.workLogType").value(DEFAULT_WORK_LOG_TYPE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLocationVM() throws Exception {
        // Get the locationVM
        restLocationVMMockMvc.perform(get("/api/location-vms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocationVM() throws Exception {
        // Initialize the database
        locationVMRepository.saveAndFlush(locationVM);

        int databaseSizeBeforeUpdate = locationVMRepository.findAll().size();

        // Update the locationVM
        LocationVM updatedLocationVM = locationVMRepository.findById(locationVM.getId()).get();
        // Disconnect from session so that the updates on updatedLocationVM are not directly saved in db
        em.detach(updatedLocationVM);
        updatedLocationVM
            .stationId(UPDATED_STATION_ID)
            .groupName(UPDATED_GROUP_NAME)
            .stationName(UPDATED_STATION_NAME)
            .longitude(UPDATED_LONGITUDE)
            .latitude(UPDATED_LATITUDE)
            .accuracy(UPDATED_ACCURACY)
            .address(UPDATED_ADDRESS)
            .province(UPDATED_PROVINCE)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .road(UPDATED_ROAD)
            .netType(UPDATED_NET_TYPE)
            .operatorType(UPDATED_OPERATOR_TYPE)
            .ddUserName(UPDATED_DD_USER_NAME)
            .ddUserId(UPDATED_DD_USER_ID)
            .ddUserPhone(UPDATED_DD_USER_PHONE)
            .isFinish(UPDATED_IS_FINISH)
            .workLogMainId(UPDATED_WORK_LOG_MAIN_ID)
            .workLogType(UPDATED_WORK_LOG_TYPE)
            .createdDate(UPDATED_CREATED_DATE);

        restLocationVMMockMvc.perform(put("/api/location-vms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocationVM)))
            .andExpect(status().isOk());

        // Validate the LocationVM in the database
        List<LocationVM> locationVMList = locationVMRepository.findAll();
        assertThat(locationVMList).hasSize(databaseSizeBeforeUpdate);
        LocationVM testLocationVM = locationVMList.get(locationVMList.size() - 1);
        assertThat(testLocationVM.getStationId()).isEqualTo(UPDATED_STATION_ID);
        assertThat(testLocationVM.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testLocationVM.getStationName()).isEqualTo(UPDATED_STATION_NAME);
        assertThat(testLocationVM.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testLocationVM.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLocationVM.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testLocationVM.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testLocationVM.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testLocationVM.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocationVM.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testLocationVM.getRoad()).isEqualTo(UPDATED_ROAD);
        assertThat(testLocationVM.getNetType()).isEqualTo(UPDATED_NET_TYPE);
        assertThat(testLocationVM.getOperatorType()).isEqualTo(UPDATED_OPERATOR_TYPE);
        assertThat(testLocationVM.getDdUserName()).isEqualTo(UPDATED_DD_USER_NAME);
        assertThat(testLocationVM.getDdUserId()).isEqualTo(UPDATED_DD_USER_ID);
        assertThat(testLocationVM.getDdUserPhone()).isEqualTo(UPDATED_DD_USER_PHONE);
        assertThat(testLocationVM.isIsFinish()).isEqualTo(UPDATED_IS_FINISH);
        assertThat(testLocationVM.getWorkLogMainId()).isEqualTo(UPDATED_WORK_LOG_MAIN_ID);
        assertThat(testLocationVM.getWorkLogType()).isEqualTo(UPDATED_WORK_LOG_TYPE);
        assertThat(testLocationVM.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocationVM() throws Exception {
        int databaseSizeBeforeUpdate = locationVMRepository.findAll().size();

        // Create the LocationVM

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationVMMockMvc.perform(put("/api/location-vms")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationVM)))
            .andExpect(status().isBadRequest());

        // Validate the LocationVM in the database
        List<LocationVM> locationVMList = locationVMRepository.findAll();
        assertThat(locationVMList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocationVM() throws Exception {
        // Initialize the database
        locationVMRepository.saveAndFlush(locationVM);

        int databaseSizeBeforeDelete = locationVMRepository.findAll().size();

        // Delete the locationVM
        restLocationVMMockMvc.perform(delete("/api/location-vms/{id}", locationVM.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LocationVM> locationVMList = locationVMRepository.findAll();
        assertThat(locationVMList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
