package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.FmpSubCompany;
import com.lazulite.base.repository.FmpSubCompanyRepository;
import com.lazulite.base.service.FmpSubCompanyService;

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
 * Integration tests for the {@link FmpSubCompanyResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FmpSubCompanyResourceIT {

    private static final String DEFAULT_MORE_TODO_URL = "AAAAAAAAAA";
    private static final String UPDATED_MORE_TODO_URL = "BBBBBBBBBB";

    @Autowired
    private FmpSubCompanyRepository fmpSubCompanyRepository;

    @Autowired
    private FmpSubCompanyService fmpSubCompanyService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFmpSubCompanyMockMvc;

    private FmpSubCompany fmpSubCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FmpSubCompany createEntity(EntityManager em) {
        FmpSubCompany fmpSubCompany = new FmpSubCompany()
            .moreTodoUrl(DEFAULT_MORE_TODO_URL);
        return fmpSubCompany;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FmpSubCompany createUpdatedEntity(EntityManager em) {
        FmpSubCompany fmpSubCompany = new FmpSubCompany()
            .moreTodoUrl(UPDATED_MORE_TODO_URL);
        return fmpSubCompany;
    }

    @BeforeEach
    public void initTest() {
        fmpSubCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createFmpSubCompany() throws Exception {
        int databaseSizeBeforeCreate = fmpSubCompanyRepository.findAll().size();
        // Create the FmpSubCompany
        restFmpSubCompanyMockMvc.perform(post("/api/fmp-sub-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpSubCompany)))
            .andExpect(status().isCreated());

        // Validate the FmpSubCompany in the database
        List<FmpSubCompany> fmpSubCompanyList = fmpSubCompanyRepository.findAll();
        assertThat(fmpSubCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        FmpSubCompany testFmpSubCompany = fmpSubCompanyList.get(fmpSubCompanyList.size() - 1);
        assertThat(testFmpSubCompany.getMoreTodoUrl()).isEqualTo(DEFAULT_MORE_TODO_URL);
    }

    @Test
    @Transactional
    public void createFmpSubCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fmpSubCompanyRepository.findAll().size();

        // Create the FmpSubCompany with an existing ID
        fmpSubCompany.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFmpSubCompanyMockMvc.perform(post("/api/fmp-sub-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpSubCompany)))
            .andExpect(status().isBadRequest());

        // Validate the FmpSubCompany in the database
        List<FmpSubCompany> fmpSubCompanyList = fmpSubCompanyRepository.findAll();
        assertThat(fmpSubCompanyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFmpSubCompanies() throws Exception {
        // Initialize the database
        fmpSubCompanyRepository.saveAndFlush(fmpSubCompany);

        // Get all the fmpSubCompanyList
        restFmpSubCompanyMockMvc.perform(get("/api/fmp-sub-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fmpSubCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].moreTodoUrl").value(hasItem(DEFAULT_MORE_TODO_URL)));
    }
    
    @Test
    @Transactional
    public void getFmpSubCompany() throws Exception {
        // Initialize the database
        fmpSubCompanyRepository.saveAndFlush(fmpSubCompany);

        // Get the fmpSubCompany
        restFmpSubCompanyMockMvc.perform(get("/api/fmp-sub-companies/{id}", fmpSubCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fmpSubCompany.getId().intValue()))
            .andExpect(jsonPath("$.moreTodoUrl").value(DEFAULT_MORE_TODO_URL));
    }
    @Test
    @Transactional
    public void getNonExistingFmpSubCompany() throws Exception {
        // Get the fmpSubCompany
        restFmpSubCompanyMockMvc.perform(get("/api/fmp-sub-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFmpSubCompany() throws Exception {
        // Initialize the database
        fmpSubCompanyService.save(fmpSubCompany);

        int databaseSizeBeforeUpdate = fmpSubCompanyRepository.findAll().size();

        // Update the fmpSubCompany
        FmpSubCompany updatedFmpSubCompany = fmpSubCompanyRepository.findById(fmpSubCompany.getId()).get();
        // Disconnect from session so that the updates on updatedFmpSubCompany are not directly saved in db
        em.detach(updatedFmpSubCompany);
        updatedFmpSubCompany
            .moreTodoUrl(UPDATED_MORE_TODO_URL);

        restFmpSubCompanyMockMvc.perform(put("/api/fmp-sub-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFmpSubCompany)))
            .andExpect(status().isOk());

        // Validate the FmpSubCompany in the database
        List<FmpSubCompany> fmpSubCompanyList = fmpSubCompanyRepository.findAll();
        assertThat(fmpSubCompanyList).hasSize(databaseSizeBeforeUpdate);
        FmpSubCompany testFmpSubCompany = fmpSubCompanyList.get(fmpSubCompanyList.size() - 1);
        assertThat(testFmpSubCompany.getMoreTodoUrl()).isEqualTo(UPDATED_MORE_TODO_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingFmpSubCompany() throws Exception {
        int databaseSizeBeforeUpdate = fmpSubCompanyRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFmpSubCompanyMockMvc.perform(put("/api/fmp-sub-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpSubCompany)))
            .andExpect(status().isBadRequest());

        // Validate the FmpSubCompany in the database
        List<FmpSubCompany> fmpSubCompanyList = fmpSubCompanyRepository.findAll();
        assertThat(fmpSubCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFmpSubCompany() throws Exception {
        // Initialize the database
        fmpSubCompanyService.save(fmpSubCompany);

        int databaseSizeBeforeDelete = fmpSubCompanyRepository.findAll().size();

        // Delete the fmpSubCompany
        restFmpSubCompanyMockMvc.perform(delete("/api/fmp-sub-companies/{id}", fmpSubCompany.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FmpSubCompany> fmpSubCompanyList = fmpSubCompanyRepository.findAll();
        assertThat(fmpSubCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
