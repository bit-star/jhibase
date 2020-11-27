package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.MicroAppGroup;
import com.lazulite.base.repository.MicroAppGroupRepository;
import com.lazulite.base.service.MicroAppGroupService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MicroAppGroupResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class MicroAppGroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private MicroAppGroupRepository microAppGroupRepository;

    @Mock
    private MicroAppGroupRepository microAppGroupRepositoryMock;

    @Mock
    private MicroAppGroupService microAppGroupServiceMock;

    @Autowired
    private MicroAppGroupService microAppGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMicroAppGroupMockMvc;

    private MicroAppGroup microAppGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MicroAppGroup createEntity(EntityManager em) {
        MicroAppGroup microAppGroup = new MicroAppGroup()
            .name(DEFAULT_NAME);
        return microAppGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MicroAppGroup createUpdatedEntity(EntityManager em) {
        MicroAppGroup microAppGroup = new MicroAppGroup()
            .name(UPDATED_NAME);
        return microAppGroup;
    }

    @BeforeEach
    public void initTest() {
        microAppGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMicroAppGroup() throws Exception {
        int databaseSizeBeforeCreate = microAppGroupRepository.findAll().size();
        // Create the MicroAppGroup
        restMicroAppGroupMockMvc.perform(post("/api/micro-app-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(microAppGroup)))
            .andExpect(status().isCreated());

        // Validate the MicroAppGroup in the database
        List<MicroAppGroup> microAppGroupList = microAppGroupRepository.findAll();
        assertThat(microAppGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MicroAppGroup testMicroAppGroup = microAppGroupList.get(microAppGroupList.size() - 1);
        assertThat(testMicroAppGroup.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createMicroAppGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = microAppGroupRepository.findAll().size();

        // Create the MicroAppGroup with an existing ID
        microAppGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMicroAppGroupMockMvc.perform(post("/api/micro-app-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(microAppGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MicroAppGroup in the database
        List<MicroAppGroup> microAppGroupList = microAppGroupRepository.findAll();
        assertThat(microAppGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMicroAppGroups() throws Exception {
        // Initialize the database
        microAppGroupRepository.saveAndFlush(microAppGroup);

        // Get all the microAppGroupList
        restMicroAppGroupMockMvc.perform(get("/api/micro-app-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(microAppGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllMicroAppGroupsWithEagerRelationshipsIsEnabled() throws Exception {
        when(microAppGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMicroAppGroupMockMvc.perform(get("/api/micro-app-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(microAppGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllMicroAppGroupsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(microAppGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMicroAppGroupMockMvc.perform(get("/api/micro-app-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(microAppGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getMicroAppGroup() throws Exception {
        // Initialize the database
        microAppGroupRepository.saveAndFlush(microAppGroup);

        // Get the microAppGroup
        restMicroAppGroupMockMvc.perform(get("/api/micro-app-groups/{id}", microAppGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(microAppGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingMicroAppGroup() throws Exception {
        // Get the microAppGroup
        restMicroAppGroupMockMvc.perform(get("/api/micro-app-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMicroAppGroup() throws Exception {
        // Initialize the database
        microAppGroupService.save(microAppGroup);

        int databaseSizeBeforeUpdate = microAppGroupRepository.findAll().size();

        // Update the microAppGroup
        MicroAppGroup updatedMicroAppGroup = microAppGroupRepository.findById(microAppGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMicroAppGroup are not directly saved in db
        em.detach(updatedMicroAppGroup);
        updatedMicroAppGroup
            .name(UPDATED_NAME);

        restMicroAppGroupMockMvc.perform(put("/api/micro-app-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMicroAppGroup)))
            .andExpect(status().isOk());

        // Validate the MicroAppGroup in the database
        List<MicroAppGroup> microAppGroupList = microAppGroupRepository.findAll();
        assertThat(microAppGroupList).hasSize(databaseSizeBeforeUpdate);
        MicroAppGroup testMicroAppGroup = microAppGroupList.get(microAppGroupList.size() - 1);
        assertThat(testMicroAppGroup.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMicroAppGroup() throws Exception {
        int databaseSizeBeforeUpdate = microAppGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMicroAppGroupMockMvc.perform(put("/api/micro-app-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(microAppGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MicroAppGroup in the database
        List<MicroAppGroup> microAppGroupList = microAppGroupRepository.findAll();
        assertThat(microAppGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMicroAppGroup() throws Exception {
        // Initialize the database
        microAppGroupService.save(microAppGroup);

        int databaseSizeBeforeDelete = microAppGroupRepository.findAll().size();

        // Delete the microAppGroup
        restMicroAppGroupMockMvc.perform(delete("/api/micro-app-groups/{id}", microAppGroup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MicroAppGroup> microAppGroupList = microAppGroupRepository.findAll();
        assertThat(microAppGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
