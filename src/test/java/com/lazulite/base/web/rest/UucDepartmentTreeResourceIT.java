package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.UucDepartmentTree;
import com.lazulite.base.repository.UucDepartmentTreeRepository;
import com.lazulite.base.service.UucDepartmentTreeService;

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
 * Integration tests for the {@link UucDepartmentTreeResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class UucDepartmentTreeResourceIT {

    @Autowired
    private UucDepartmentTreeRepository uucDepartmentTreeRepository;

    @Autowired
    private UucDepartmentTreeService uucDepartmentTreeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUucDepartmentTreeMockMvc;

    private UucDepartmentTree uucDepartmentTree;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UucDepartmentTree createEntity(EntityManager em) {
        UucDepartmentTree uucDepartmentTree = new UucDepartmentTree();
        return uucDepartmentTree;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UucDepartmentTree createUpdatedEntity(EntityManager em) {
        UucDepartmentTree uucDepartmentTree = new UucDepartmentTree();
        return uucDepartmentTree;
    }

    @BeforeEach
    public void initTest() {
        uucDepartmentTree = createEntity(em);
    }

    @Test
    @Transactional
    public void createUucDepartmentTree() throws Exception {
        int databaseSizeBeforeCreate = uucDepartmentTreeRepository.findAll().size();

        // Create the UucDepartmentTree
        restUucDepartmentTreeMockMvc.perform(post("/api/uuc-department-trees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucDepartmentTree)))
            .andExpect(status().isCreated());

        // Validate the UucDepartmentTree in the database
        List<UucDepartmentTree> uucDepartmentTreeList = uucDepartmentTreeRepository.findAll();
        assertThat(uucDepartmentTreeList).hasSize(databaseSizeBeforeCreate + 1);
        UucDepartmentTree testUucDepartmentTree = uucDepartmentTreeList.get(uucDepartmentTreeList.size() - 1);
    }

    @Test
    @Transactional
    public void createUucDepartmentTreeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uucDepartmentTreeRepository.findAll().size();

        // Create the UucDepartmentTree with an existing ID
        uucDepartmentTree.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUucDepartmentTreeMockMvc.perform(post("/api/uuc-department-trees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucDepartmentTree)))
            .andExpect(status().isBadRequest());

        // Validate the UucDepartmentTree in the database
        List<UucDepartmentTree> uucDepartmentTreeList = uucDepartmentTreeRepository.findAll();
        assertThat(uucDepartmentTreeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUucDepartmentTrees() throws Exception {
        // Initialize the database
        uucDepartmentTreeRepository.saveAndFlush(uucDepartmentTree);

        // Get all the uucDepartmentTreeList
        restUucDepartmentTreeMockMvc.perform(get("/api/uuc-department-trees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uucDepartmentTree.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getUucDepartmentTree() throws Exception {
        // Initialize the database
        uucDepartmentTreeRepository.saveAndFlush(uucDepartmentTree);

        // Get the uucDepartmentTree
        restUucDepartmentTreeMockMvc.perform(get("/api/uuc-department-trees/{id}", uucDepartmentTree.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(uucDepartmentTree.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUucDepartmentTree() throws Exception {
        // Get the uucDepartmentTree
        restUucDepartmentTreeMockMvc.perform(get("/api/uuc-department-trees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUucDepartmentTree() throws Exception {
        // Initialize the database
        uucDepartmentTreeService.save(uucDepartmentTree);

        int databaseSizeBeforeUpdate = uucDepartmentTreeRepository.findAll().size();

        // Update the uucDepartmentTree
        UucDepartmentTree updatedUucDepartmentTree = uucDepartmentTreeRepository.findById(uucDepartmentTree.getId()).get();
        // Disconnect from session so that the updates on updatedUucDepartmentTree are not directly saved in db
        em.detach(updatedUucDepartmentTree);

        restUucDepartmentTreeMockMvc.perform(put("/api/uuc-department-trees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUucDepartmentTree)))
            .andExpect(status().isOk());

        // Validate the UucDepartmentTree in the database
        List<UucDepartmentTree> uucDepartmentTreeList = uucDepartmentTreeRepository.findAll();
        assertThat(uucDepartmentTreeList).hasSize(databaseSizeBeforeUpdate);
        UucDepartmentTree testUucDepartmentTree = uucDepartmentTreeList.get(uucDepartmentTreeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingUucDepartmentTree() throws Exception {
        int databaseSizeBeforeUpdate = uucDepartmentTreeRepository.findAll().size();

        // Create the UucDepartmentTree

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUucDepartmentTreeMockMvc.perform(put("/api/uuc-department-trees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(uucDepartmentTree)))
            .andExpect(status().isBadRequest());

        // Validate the UucDepartmentTree in the database
        List<UucDepartmentTree> uucDepartmentTreeList = uucDepartmentTreeRepository.findAll();
        assertThat(uucDepartmentTreeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUucDepartmentTree() throws Exception {
        // Initialize the database
        uucDepartmentTreeService.save(uucDepartmentTree);

        int databaseSizeBeforeDelete = uucDepartmentTreeRepository.findAll().size();

        // Delete the uucDepartmentTree
        restUucDepartmentTreeMockMvc.perform(delete("/api/uuc-department-trees/{id}", uucDepartmentTree.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UucDepartmentTree> uucDepartmentTreeList = uucDepartmentTreeRepository.findAll();
        assertThat(uucDepartmentTreeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
