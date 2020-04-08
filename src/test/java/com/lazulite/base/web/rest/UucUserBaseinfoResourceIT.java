package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.UucUserBaseinfo;
import com.lazulite.base.repository.UucUserBaseinfoRepository;
import com.lazulite.base.service.UucUserBaseinfoService;

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
 * Integration tests for the {@link UucUserBaseinfoResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UucUserBaseinfoResourceIT {

    @Autowired
    private UucUserBaseinfoRepository uucUserBaseinfoRepository;

    @Autowired
    private UucUserBaseinfoService uucUserBaseinfoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUucUserBaseinfoMockMvc;

    private UucUserBaseinfo uucUserBaseinfo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UucUserBaseinfo createEntity(EntityManager em) {
        UucUserBaseinfo uucUserBaseinfo = new UucUserBaseinfo();
        return uucUserBaseinfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UucUserBaseinfo createUpdatedEntity(EntityManager em) {
        UucUserBaseinfo uucUserBaseinfo = new UucUserBaseinfo();
        return uucUserBaseinfo;
    }

    @BeforeEach
    public void initTest() {
        uucUserBaseinfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createUucUserBaseinfo() throws Exception {
        int databaseSizeBeforeCreate = uucUserBaseinfoRepository.findAll().size();

        // Create the UucUserBaseinfo
        restUucUserBaseinfoMockMvc.perform(post("/api/uuc-user-baseinfos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucUserBaseinfo)))
            .andExpect(status().isCreated());

        // Validate the UucUserBaseinfo in the database
        List<UucUserBaseinfo> uucUserBaseinfoList = uucUserBaseinfoRepository.findAll();
        assertThat(uucUserBaseinfoList).hasSize(databaseSizeBeforeCreate + 1);
        UucUserBaseinfo testUucUserBaseinfo = uucUserBaseinfoList.get(uucUserBaseinfoList.size() - 1);
    }

    @Test
    @Transactional
    public void createUucUserBaseinfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uucUserBaseinfoRepository.findAll().size();

        // Create the UucUserBaseinfo with an existing ID
        uucUserBaseinfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUucUserBaseinfoMockMvc.perform(post("/api/uuc-user-baseinfos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucUserBaseinfo)))
            .andExpect(status().isBadRequest());

        // Validate the UucUserBaseinfo in the database
        List<UucUserBaseinfo> uucUserBaseinfoList = uucUserBaseinfoRepository.findAll();
        assertThat(uucUserBaseinfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUucUserBaseinfos() throws Exception {
        // Initialize the database
        uucUserBaseinfoRepository.saveAndFlush(uucUserBaseinfo);

        // Get all the uucUserBaseinfoList
        restUucUserBaseinfoMockMvc.perform(get("/api/uuc-user-baseinfos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uucUserBaseinfo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUucUserBaseinfo() throws Exception {
        // Initialize the database
        uucUserBaseinfoRepository.saveAndFlush(uucUserBaseinfo);

        // Get the uucUserBaseinfo
        restUucUserBaseinfoMockMvc.perform(get("/api/uuc-user-baseinfos/{id}", uucUserBaseinfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uucUserBaseinfo.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUucUserBaseinfo() throws Exception {
        // Get the uucUserBaseinfo
        restUucUserBaseinfoMockMvc.perform(get("/api/uuc-user-baseinfos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUucUserBaseinfo() throws Exception {
        // Initialize the database
        uucUserBaseinfoService.save(uucUserBaseinfo);

        int databaseSizeBeforeUpdate = uucUserBaseinfoRepository.findAll().size();

        // Update the uucUserBaseinfo
        UucUserBaseinfo updatedUucUserBaseinfo = uucUserBaseinfoRepository.findById(uucUserBaseinfo.getId()).get();
        // Disconnect from session so that the updates on updatedUucUserBaseinfo are not directly saved in db
        em.detach(updatedUucUserBaseinfo);

        restUucUserBaseinfoMockMvc.perform(put("/api/uuc-user-baseinfos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUucUserBaseinfo)))
            .andExpect(status().isOk());

        // Validate the UucUserBaseinfo in the database
        List<UucUserBaseinfo> uucUserBaseinfoList = uucUserBaseinfoRepository.findAll();
        assertThat(uucUserBaseinfoList).hasSize(databaseSizeBeforeUpdate);
        UucUserBaseinfo testUucUserBaseinfo = uucUserBaseinfoList.get(uucUserBaseinfoList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUucUserBaseinfo() throws Exception {
        int databaseSizeBeforeUpdate = uucUserBaseinfoRepository.findAll().size();

        // Create the UucUserBaseinfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUucUserBaseinfoMockMvc.perform(put("/api/uuc-user-baseinfos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucUserBaseinfo)))
            .andExpect(status().isBadRequest());

        // Validate the UucUserBaseinfo in the database
        List<UucUserBaseinfo> uucUserBaseinfoList = uucUserBaseinfoRepository.findAll();
        assertThat(uucUserBaseinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUucUserBaseinfo() throws Exception {
        // Initialize the database
        uucUserBaseinfoService.save(uucUserBaseinfo);

        int databaseSizeBeforeDelete = uucUserBaseinfoRepository.findAll().size();

        // Delete the uucUserBaseinfo
        restUucUserBaseinfoMockMvc.perform(delete("/api/uuc-user-baseinfos/{id}", uucUserBaseinfo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UucUserBaseinfo> uucUserBaseinfoList = uucUserBaseinfoRepository.findAll();
        assertThat(uucUserBaseinfoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
