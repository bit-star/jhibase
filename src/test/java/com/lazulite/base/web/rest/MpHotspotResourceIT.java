package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.MpHotspot;
import com.lazulite.base.repository.MpHotspotRepository;
import com.lazulite.base.service.MpHotspotService;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MpHotspotResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MpHotspotResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PATH_URL = "AAAAAAAAAA";
    private static final String UPDATED_PATH_URL = "BBBBBBBBBB";

    private static final Instant DEFAULT_ADD_TIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADD_TIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_ORDER_NUM = 1L;
    private static final Long UPDATED_ORDER_NUM = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private MpHotspotRepository mpHotspotRepository;

    @Autowired
    private MpHotspotService mpHotspotService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMpHotspotMockMvc;

    private MpHotspot mpHotspot;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MpHotspot createEntity(EntityManager em) {
        MpHotspot mpHotspot = new MpHotspot()
            .name(DEFAULT_NAME)
            .imageUrl(DEFAULT_IMAGE_URL)
            .pathUrl(DEFAULT_PATH_URL)
            .addTime(DEFAULT_ADD_TIME)
            .orderNum(DEFAULT_ORDER_NUM)
            .remark(DEFAULT_REMARK);
        return mpHotspot;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MpHotspot createUpdatedEntity(EntityManager em) {
        MpHotspot mpHotspot = new MpHotspot()
            .name(UPDATED_NAME)
            .imageUrl(UPDATED_IMAGE_URL)
            .pathUrl(UPDATED_PATH_URL)
            .addTime(UPDATED_ADD_TIME)
            .orderNum(UPDATED_ORDER_NUM)
            .remark(UPDATED_REMARK);
        return mpHotspot;
    }

    @BeforeEach
    public void initTest() {
        mpHotspot = createEntity(em);
    }

    @Test
    @Transactional
    public void createMpHotspot() throws Exception {
        int databaseSizeBeforeCreate = mpHotspotRepository.findAll().size();
        // Create the MpHotspot
        restMpHotspotMockMvc.perform(post("/api/mp-hotspots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mpHotspot)))
            .andExpect(status().isCreated());

        // Validate the MpHotspot in the database
        List<MpHotspot> mpHotspotList = mpHotspotRepository.findAll();
        assertThat(mpHotspotList).hasSize(databaseSizeBeforeCreate + 1);
        MpHotspot testMpHotspot = mpHotspotList.get(mpHotspotList.size() - 1);
        assertThat(testMpHotspot.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMpHotspot.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testMpHotspot.getPathUrl()).isEqualTo(DEFAULT_PATH_URL);
        assertThat(testMpHotspot.getAddTime()).isEqualTo(DEFAULT_ADD_TIME);
        assertThat(testMpHotspot.getOrderNum()).isEqualTo(DEFAULT_ORDER_NUM);
        assertThat(testMpHotspot.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createMpHotspotWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mpHotspotRepository.findAll().size();

        // Create the MpHotspot with an existing ID
        mpHotspot.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMpHotspotMockMvc.perform(post("/api/mp-hotspots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mpHotspot)))
            .andExpect(status().isBadRequest());

        // Validate the MpHotspot in the database
        List<MpHotspot> mpHotspotList = mpHotspotRepository.findAll();
        assertThat(mpHotspotList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMpHotspots() throws Exception {
        // Initialize the database
        mpHotspotRepository.saveAndFlush(mpHotspot);

        // Get all the mpHotspotList
        restMpHotspotMockMvc.perform(get("/api/mp-hotspots?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mpHotspot.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].pathUrl").value(hasItem(DEFAULT_PATH_URL)))
            .andExpect(jsonPath("$.[*].addTime").value(hasItem(DEFAULT_ADD_TIME.toString())))
            .andExpect(jsonPath("$.[*].orderNum").value(hasItem(DEFAULT_ORDER_NUM.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }
    
    @Test
    @Transactional
    public void getMpHotspot() throws Exception {
        // Initialize the database
        mpHotspotRepository.saveAndFlush(mpHotspot);

        // Get the mpHotspot
        restMpHotspotMockMvc.perform(get("/api/mp-hotspots/{id}", mpHotspot.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mpHotspot.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.pathUrl").value(DEFAULT_PATH_URL))
            .andExpect(jsonPath("$.addTime").value(DEFAULT_ADD_TIME.toString()))
            .andExpect(jsonPath("$.orderNum").value(DEFAULT_ORDER_NUM.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }
    @Test
    @Transactional
    public void getNonExistingMpHotspot() throws Exception {
        // Get the mpHotspot
        restMpHotspotMockMvc.perform(get("/api/mp-hotspots/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMpHotspot() throws Exception {
        // Initialize the database
        mpHotspotService.save(mpHotspot);

        int databaseSizeBeforeUpdate = mpHotspotRepository.findAll().size();

        // Update the mpHotspot
        MpHotspot updatedMpHotspot = mpHotspotRepository.findById(mpHotspot.getId()).get();
        // Disconnect from session so that the updates on updatedMpHotspot are not directly saved in db
        em.detach(updatedMpHotspot);
        updatedMpHotspot
            .name(UPDATED_NAME)
            .imageUrl(UPDATED_IMAGE_URL)
            .pathUrl(UPDATED_PATH_URL)
            .addTime(UPDATED_ADD_TIME)
            .orderNum(UPDATED_ORDER_NUM)
            .remark(UPDATED_REMARK);

        restMpHotspotMockMvc.perform(put("/api/mp-hotspots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMpHotspot)))
            .andExpect(status().isOk());

        // Validate the MpHotspot in the database
        List<MpHotspot> mpHotspotList = mpHotspotRepository.findAll();
        assertThat(mpHotspotList).hasSize(databaseSizeBeforeUpdate);
        MpHotspot testMpHotspot = mpHotspotList.get(mpHotspotList.size() - 1);
        assertThat(testMpHotspot.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMpHotspot.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testMpHotspot.getPathUrl()).isEqualTo(UPDATED_PATH_URL);
        assertThat(testMpHotspot.getAddTime()).isEqualTo(UPDATED_ADD_TIME);
        assertThat(testMpHotspot.getOrderNum()).isEqualTo(UPDATED_ORDER_NUM);
        assertThat(testMpHotspot.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingMpHotspot() throws Exception {
        int databaseSizeBeforeUpdate = mpHotspotRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMpHotspotMockMvc.perform(put("/api/mp-hotspots")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mpHotspot)))
            .andExpect(status().isBadRequest());

        // Validate the MpHotspot in the database
        List<MpHotspot> mpHotspotList = mpHotspotRepository.findAll();
        assertThat(mpHotspotList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMpHotspot() throws Exception {
        // Initialize the database
        mpHotspotService.save(mpHotspot);

        int databaseSizeBeforeDelete = mpHotspotRepository.findAll().size();

        // Delete the mpHotspot
        restMpHotspotMockMvc.perform(delete("/api/mp-hotspots/{id}", mpHotspot.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MpHotspot> mpHotspotList = mpHotspotRepository.findAll();
        assertThat(mpHotspotList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
