package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.LocationDTO;
import com.lazulite.base.repository.LocationDTORepository;
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
 * Integration tests for the {@link LocationDTOResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class LocationDTOResourceIT {

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
    private LocationDTORepository locationDTORepository;

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

    private MockMvc restLocationDTOMockMvc;

    private LocationDTO locationDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationDTOResource locationDTOResource = new LocationDTOResource(locationDTORepository);
        this.restLocationDTOMockMvc = MockMvcBuilders.standaloneSetup(locationDTOResource)
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
    public static LocationDTO createEntity(EntityManager em) {
        LocationDTO locationDTO = new LocationDTO()
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
        return locationDTO;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocationDTO createUpdatedEntity(EntityManager em) {
        LocationDTO locationDTO = new LocationDTO()
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
        return locationDTO;
    }

    @BeforeEach
    public void initTest() {
        locationDTO = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocationDTO() throws Exception {
        int databaseSizeBeforeCreate = locationDTORepository.findAll().size();

        // Create the LocationDTO
        restLocationDTOMockMvc.perform(post("/api/location-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isCreated());

        // Validate the LocationDTO in the database
        List<LocationDTO> locationDTOList = locationDTORepository.findAll();
        assertThat(locationDTOList).hasSize(databaseSizeBeforeCreate + 1);
        LocationDTO testLocationDTO = locationDTOList.get(locationDTOList.size() - 1);
        assertThat(testLocationDTO.getStationId()).isEqualTo(DEFAULT_STATION_ID);
        assertThat(testLocationDTO.getGroupName()).isEqualTo(DEFAULT_GROUP_NAME);
        assertThat(testLocationDTO.getStationName()).isEqualTo(DEFAULT_STATION_NAME);
        assertThat(testLocationDTO.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testLocationDTO.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testLocationDTO.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testLocationDTO.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testLocationDTO.getProvince()).isEqualTo(DEFAULT_PROVINCE);
        assertThat(testLocationDTO.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLocationDTO.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testLocationDTO.getRoad()).isEqualTo(DEFAULT_ROAD);
        assertThat(testLocationDTO.getNetType()).isEqualTo(DEFAULT_NET_TYPE);
        assertThat(testLocationDTO.getOperatorType()).isEqualTo(DEFAULT_OPERATOR_TYPE);
        assertThat(testLocationDTO.getDdUserName()).isEqualTo(DEFAULT_DD_USER_NAME);
        assertThat(testLocationDTO.getDdUserId()).isEqualTo(DEFAULT_DD_USER_ID);
        assertThat(testLocationDTO.getDdUserPhone()).isEqualTo(DEFAULT_DD_USER_PHONE);
        assertThat(testLocationDTO.isIsFinish()).isEqualTo(DEFAULT_IS_FINISH);
        assertThat(testLocationDTO.getWorkLogMainId()).isEqualTo(DEFAULT_WORK_LOG_MAIN_ID);
        assertThat(testLocationDTO.getWorkLogType()).isEqualTo(DEFAULT_WORK_LOG_TYPE);
        assertThat(testLocationDTO.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    }

    @Test
    @Transactional
    public void createLocationDTOWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationDTORepository.findAll().size();

        // Create the LocationDTO with an existing ID
        locationDTO.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationDTOMockMvc.perform(post("/api/location-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationDTO in the database
        List<LocationDTO> locationDTOList = locationDTORepository.findAll();
        assertThat(locationDTOList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLocationDTOS() throws Exception {
        // Initialize the database
        locationDTORepository.saveAndFlush(locationDTO);

        // Get all the locationDTOList
        restLocationDTOMockMvc.perform(get("/api/location-dtos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationDTO.getId().intValue())))
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
    public void getLocationDTO() throws Exception {
        // Initialize the database
        locationDTORepository.saveAndFlush(locationDTO);

        // Get the locationDTO
        restLocationDTOMockMvc.perform(get("/api/location-dtos/{id}", locationDTO.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locationDTO.getId().intValue()))
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
    public void getNonExistingLocationDTO() throws Exception {
        // Get the locationDTO
        restLocationDTOMockMvc.perform(get("/api/location-dtos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocationDTO() throws Exception {
        // Initialize the database
        locationDTORepository.saveAndFlush(locationDTO);

        int databaseSizeBeforeUpdate = locationDTORepository.findAll().size();

        // Update the locationDTO
        LocationDTO updatedLocationDTO = locationDTORepository.findById(locationDTO.getId()).get();
        // Disconnect from session so that the updates on updatedLocationDTO are not directly saved in db
        em.detach(updatedLocationDTO);
        updatedLocationDTO
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

        restLocationDTOMockMvc.perform(put("/api/location-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLocationDTO)))
            .andExpect(status().isOk());

        // Validate the LocationDTO in the database
        List<LocationDTO> locationDTOList = locationDTORepository.findAll();
        assertThat(locationDTOList).hasSize(databaseSizeBeforeUpdate);
        LocationDTO testLocationDTO = locationDTOList.get(locationDTOList.size() - 1);
        assertThat(testLocationDTO.getStationId()).isEqualTo(UPDATED_STATION_ID);
        assertThat(testLocationDTO.getGroupName()).isEqualTo(UPDATED_GROUP_NAME);
        assertThat(testLocationDTO.getStationName()).isEqualTo(UPDATED_STATION_NAME);
        assertThat(testLocationDTO.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testLocationDTO.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testLocationDTO.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testLocationDTO.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testLocationDTO.getProvince()).isEqualTo(UPDATED_PROVINCE);
        assertThat(testLocationDTO.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLocationDTO.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testLocationDTO.getRoad()).isEqualTo(UPDATED_ROAD);
        assertThat(testLocationDTO.getNetType()).isEqualTo(UPDATED_NET_TYPE);
        assertThat(testLocationDTO.getOperatorType()).isEqualTo(UPDATED_OPERATOR_TYPE);
        assertThat(testLocationDTO.getDdUserName()).isEqualTo(UPDATED_DD_USER_NAME);
        assertThat(testLocationDTO.getDdUserId()).isEqualTo(UPDATED_DD_USER_ID);
        assertThat(testLocationDTO.getDdUserPhone()).isEqualTo(UPDATED_DD_USER_PHONE);
        assertThat(testLocationDTO.isIsFinish()).isEqualTo(UPDATED_IS_FINISH);
        assertThat(testLocationDTO.getWorkLogMainId()).isEqualTo(UPDATED_WORK_LOG_MAIN_ID);
        assertThat(testLocationDTO.getWorkLogType()).isEqualTo(UPDATED_WORK_LOG_TYPE);
        assertThat(testLocationDTO.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLocationDTO() throws Exception {
        int databaseSizeBeforeUpdate = locationDTORepository.findAll().size();

        // Create the LocationDTO

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationDTOMockMvc.perform(put("/api/location-dtos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationDTO in the database
        List<LocationDTO> locationDTOList = locationDTORepository.findAll();
        assertThat(locationDTOList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocationDTO() throws Exception {
        // Initialize the database
        locationDTORepository.saveAndFlush(locationDTO);

        int databaseSizeBeforeDelete = locationDTORepository.findAll().size();

        // Delete the locationDTO
        restLocationDTOMockMvc.perform(delete("/api/location-dtos/{id}", locationDTO.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LocationDTO> locationDTOList = locationDTORepository.findAll();
        assertThat(locationDTOList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
