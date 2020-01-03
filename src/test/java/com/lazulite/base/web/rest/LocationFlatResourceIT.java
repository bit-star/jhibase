package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.LocationFlat;
import com.lazulite.base.repository.LocationFlatRepository;
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
 * Integration tests for the {@link LocationFlatResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class LocationFlatResourceIT {

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
    private LocationFlatRepository locationFlatRepository;

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

    private MockMvc restLocationFlatMockMvc;

    private LocationFlat locationFlat;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationFlatResource locationFlatResource = new LocationFlatResource(locationFlatRepository);
        this.restLocationFlatMockMvc = MockMvcBuilders.standaloneSetup(locationFlatResource)
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
    public static LocationFlat createEntity(EntityManager em) {
        LocationFlat locationFlat = new LocationFlat()
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
        return locationFlat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocationFlat createUpdatedEntity(EntityManager em) {
        LocationFlat locationFlat = new LocationFlat()
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
        return locationFlat;
    }

    @BeforeEach
    public void initTest() {
        locationFlat = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocationFlat() throws Exception {
        int databaseSizeBeforeCreate = locationFlatRepository.findAll().size();

        // Create the LocationFlat
        restLocationFlatMockMvc.perform(post("/api/location-flats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationFlat)))
            .andExpect(status().isCreated());

        // Validate the LocationFlat in the database
        List<LocationFlat> locationFlatList = locationFlatRepository.findAll();
        assertThat(locationFlatList).hasSize(databaseSizeBeforeCreate + 1);
        LocationFlat testLocationFlat = locationFlatList.get(locationFlatList.size() - 1);
        assertThat(testLocationFlat.getStationId()).isEqualTo(DEFAULT_STATION_ID);
        assertThat(testLocationFlat.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testLocationFlat.getStationName()).isEqualTo(DEFAULT_STATION_NAME);
        assertThat(testLocationFlat.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testLocationFlat.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLocationFlat.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testLocationFlat.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testLocationFlat.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testLocationFlat.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocationFlat.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testLocationFlat.getRoad()).isEqualTo(DEFAULT_ROAD);
        assertThat(testLocationFlat.getNetType()).isEqualTo(DEFAULT_NET_TYPE);
        assertThat(testLocationFlat.getOperatorType()).isEqualTo(DEFAULT_OPERATOR_TYPE);
        assertThat(testLocationFlat.getDdUserName()).isEqualTo(DEFAULT_DD_USER_NAME);
        assertThat(testLocationFlat.getDdUserId()).isEqualTo(DEFAULT_DD_USER_ID);
        assertThat(testLocationFlat.getDdUserPhone()).isEqualTo(DEFAULT_DD_USER_PHONE);
        assertThat(testLocationFlat.isIsFinish()).isEqualTo(DEFAULT_IS_FINISH);
        assertThat(testLocationFlat.getWorkLogMainId()).isEqualTo(DEFAULT_WORK_LOG_MAIN_ID);
        assertThat(testLocationFlat.getWorkLogType()).isEqualTo(DEFAULT_WORK_LOG_TYPE);
        assertThat(testLocationFlat.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createLocationFlatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationFlatRepository.findAll().size();

        // Create the LocationFlat with an existing ID
        locationFlat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationFlatMockMvc.perform(post("/api/location-flats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationFlat)))
            .andExpect(status().isBadRequest());

        // Validate the LocationFlat in the database
        List<LocationFlat> locationFlatList = locationFlatRepository.findAll();
        assertThat(locationFlatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocationFlats() throws Exception {
        // Initialize the database
        locationFlatRepository.saveAndFlush(locationFlat);

        // Get all the locationFlatList
        restLocationFlatMockMvc.perform(get("/api/location-flats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationFlat.getId().intValue())))
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
    public void getLocationFlat() throws Exception {
        // Initialize the database
        locationFlatRepository.saveAndFlush(locationFlat);

        // Get the locationFlat
        restLocationFlatMockMvc.perform(get("/api/location-flats/{id}", locationFlat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locationFlat.getId().intValue()))
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
    public void getNonExistingLocationFlat() throws Exception {
        // Get the locationFlat
        restLocationFlatMockMvc.perform(get("/api/location-flats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocationFlat() throws Exception {
        // Initialize the database
        locationFlatRepository.saveAndFlush(locationFlat);

        int databaseSizeBeforeUpdate = locationFlatRepository.findAll().size();

        // Update the locationFlat
        LocationFlat updatedLocationFlat = locationFlatRepository.findById(locationFlat.getId()).get();
        // Disconnect from session so that the updates on updatedLocationFlat are not directly saved in db
        em.detach(updatedLocationFlat);
        updatedLocationFlat
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

        restLocationFlatMockMvc.perform(put("/api/location-flats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocationFlat)))
            .andExpect(status().isOk());

        // Validate the LocationFlat in the database
        List<LocationFlat> locationFlatList = locationFlatRepository.findAll();
        assertThat(locationFlatList).hasSize(databaseSizeBeforeUpdate);
        LocationFlat testLocationFlat = locationFlatList.get(locationFlatList.size() - 1);
        assertThat(testLocationFlat.getStationId()).isEqualTo(UPDATED_STATION_ID);
        assertThat(testLocationFlat.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testLocationFlat.getStationName()).isEqualTo(UPDATED_STATION_NAME);
        assertThat(testLocationFlat.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testLocationFlat.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLocationFlat.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testLocationFlat.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testLocationFlat.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testLocationFlat.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocationFlat.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testLocationFlat.getRoad()).isEqualTo(UPDATED_ROAD);
        assertThat(testLocationFlat.getNetType()).isEqualTo(UPDATED_NET_TYPE);
        assertThat(testLocationFlat.getOperatorType()).isEqualTo(UPDATED_OPERATOR_TYPE);
        assertThat(testLocationFlat.getDdUserName()).isEqualTo(UPDATED_DD_USER_NAME);
        assertThat(testLocationFlat.getDdUserId()).isEqualTo(UPDATED_DD_USER_ID);
        assertThat(testLocationFlat.getDdUserPhone()).isEqualTo(UPDATED_DD_USER_PHONE);
        assertThat(testLocationFlat.isIsFinish()).isEqualTo(UPDATED_IS_FINISH);
        assertThat(testLocationFlat.getWorkLogMainId()).isEqualTo(UPDATED_WORK_LOG_MAIN_ID);
        assertThat(testLocationFlat.getWorkLogType()).isEqualTo(UPDATED_WORK_LOG_TYPE);
        assertThat(testLocationFlat.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocationFlat() throws Exception {
        int databaseSizeBeforeUpdate = locationFlatRepository.findAll().size();

        // Create the LocationFlat

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationFlatMockMvc.perform(put("/api/location-flats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationFlat)))
            .andExpect(status().isBadRequest());

        // Validate the LocationFlat in the database
        List<LocationFlat> locationFlatList = locationFlatRepository.findAll();
        assertThat(locationFlatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocationFlat() throws Exception {
        // Initialize the database
        locationFlatRepository.saveAndFlush(locationFlat);

        int databaseSizeBeforeDelete = locationFlatRepository.findAll().size();

        // Delete the locationFlat
        restLocationFlatMockMvc.perform(delete("/api/location-flats/{id}", locationFlat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LocationFlat> locationFlatList = locationFlatRepository.findAll();
        assertThat(locationFlatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
