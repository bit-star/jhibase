package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.PushSubject;
import com.lazulite.base.repository.PushSubjectRepository;
import com.lazulite.base.service.PushSubjectService;

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
 * Integration tests for the {@link PushSubjectResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PushSubjectResourceIT {

    private static final Long DEFAULT_AGENT_ID = 1L;
    private static final Long UPDATED_AGENT_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_GROUP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE_COLOR = "AAAAAAAAAA";
    private static final String UPDATED_TITLE_COLOR = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private PushSubjectRepository pushSubjectRepository;

    @Autowired
    private PushSubjectService pushSubjectService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPushSubjectMockMvc;

    private PushSubject pushSubject;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PushSubject createEntity(EntityManager em) {
        PushSubject pushSubject = new PushSubject()
            .agentId(DEFAULT_AGENT_ID)
            .name(DEFAULT_NAME)
            .agentGroupId(DEFAULT_AGENT_GROUP_ID)
            .titleColor(DEFAULT_TITLE_COLOR)
            .remark(DEFAULT_REMARK);
        return pushSubject;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PushSubject createUpdatedEntity(EntityManager em) {
        PushSubject pushSubject = new PushSubject()
            .agentId(UPDATED_AGENT_ID)
            .name(UPDATED_NAME)
            .agentGroupId(UPDATED_AGENT_GROUP_ID)
            .titleColor(UPDATED_TITLE_COLOR)
            .remark(UPDATED_REMARK);
        return pushSubject;
    }

    @BeforeEach
    public void initTest() {
        pushSubject = createEntity(em);
    }

    @Test
    @Transactional
    public void createPushSubject() throws Exception {
        int databaseSizeBeforeCreate = pushSubjectRepository.findAll().size();

        // Create the PushSubject
        restPushSubjectMockMvc.perform(post("/api/push-subjects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pushSubject)))
            .andExpect(status().isCreated());

        // Validate the PushSubject in the database
        List<PushSubject> pushSubjectList = pushSubjectRepository.findAll();
        assertThat(pushSubjectList).hasSize(databaseSizeBeforeCreate + 1);
        PushSubject testPushSubject = pushSubjectList.get(pushSubjectList.size() - 1);
        assertThat(testPushSubject.getAgentId()).isEqualTo(DEFAULT_AGENT_ID);
        assertThat(testPushSubject.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPushSubject.getAgentGroupId()).isEqualTo(DEFAULT_AGENT_GROUP_ID);
        assertThat(testPushSubject.getTitleColor()).isEqualTo(DEFAULT_TITLE_COLOR);
        assertThat(testPushSubject.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createPushSubjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pushSubjectRepository.findAll().size();

        // Create the PushSubject with an existing ID
        pushSubject.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPushSubjectMockMvc.perform(post("/api/push-subjects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pushSubject)))
            .andExpect(status().isBadRequest());

        // Validate the PushSubject in the database
        List<PushSubject> pushSubjectList = pushSubjectRepository.findAll();
        assertThat(pushSubjectList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPushSubjects() throws Exception {
        // Initialize the database
        pushSubjectRepository.saveAndFlush(pushSubject);

        // Get all the pushSubjectList
        restPushSubjectMockMvc.perform(get("/api/push-subjects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pushSubject.getId().intValue())))
            .andExpect(jsonPath("$.[*].agentId").value(hasItem(DEFAULT_AGENT_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].agentGroupId").value(hasItem(DEFAULT_AGENT_GROUP_ID)))
            .andExpect(jsonPath("$.[*].titleColor").value(hasItem(DEFAULT_TITLE_COLOR)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }
    
    @Test
    @Transactional
    public void getPushSubject() throws Exception {
        // Initialize the database
        pushSubjectRepository.saveAndFlush(pushSubject);

        // Get the pushSubject
        restPushSubjectMockMvc.perform(get("/api/push-subjects/{id}", pushSubject.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pushSubject.getId().intValue()))
            .andExpect(jsonPath("$.agentId").value(DEFAULT_AGENT_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.agentGroupId").value(DEFAULT_AGENT_GROUP_ID))
            .andExpect(jsonPath("$.titleColor").value(DEFAULT_TITLE_COLOR))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    public void getNonExistingPushSubject() throws Exception {
        // Get the pushSubject
        restPushSubjectMockMvc.perform(get("/api/push-subjects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePushSubject() throws Exception {
        // Initialize the database
        pushSubjectService.save(pushSubject);

        int databaseSizeBeforeUpdate = pushSubjectRepository.findAll().size();

        // Update the pushSubject
        PushSubject updatedPushSubject = pushSubjectRepository.findById(pushSubject.getId()).get();
        // Disconnect from session so that the updates on updatedPushSubject are not directly saved in db
        em.detach(updatedPushSubject);
        updatedPushSubject
            .agentId(UPDATED_AGENT_ID)
            .name(UPDATED_NAME)
            .agentGroupId(UPDATED_AGENT_GROUP_ID)
            .titleColor(UPDATED_TITLE_COLOR)
            .remark(UPDATED_REMARK);

        restPushSubjectMockMvc.perform(put("/api/push-subjects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPushSubject)))
            .andExpect(status().isOk());

        // Validate the PushSubject in the database
        List<PushSubject> pushSubjectList = pushSubjectRepository.findAll();
        assertThat(pushSubjectList).hasSize(databaseSizeBeforeUpdate);
        PushSubject testPushSubject = pushSubjectList.get(pushSubjectList.size() - 1);
        assertThat(testPushSubject.getAgentId()).isEqualTo(UPDATED_AGENT_ID);
        assertThat(testPushSubject.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPushSubject.getAgentGroupId()).isEqualTo(UPDATED_AGENT_GROUP_ID);
        assertThat(testPushSubject.getTitleColor()).isEqualTo(UPDATED_TITLE_COLOR);
        assertThat(testPushSubject.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingPushSubject() throws Exception {
        int databaseSizeBeforeUpdate = pushSubjectRepository.findAll().size();

        // Create the PushSubject

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPushSubjectMockMvc.perform(put("/api/push-subjects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(pushSubject)))
            .andExpect(status().isBadRequest());

        // Validate the PushSubject in the database
        List<PushSubject> pushSubjectList = pushSubjectRepository.findAll();
        assertThat(pushSubjectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePushSubject() throws Exception {
        // Initialize the database
        pushSubjectService.save(pushSubject);

        int databaseSizeBeforeDelete = pushSubjectRepository.findAll().size();

        // Delete the pushSubject
        restPushSubjectMockMvc.perform(delete("/api/push-subjects/{id}", pushSubject.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PushSubject> pushSubjectList = pushSubjectRepository.findAll();
        assertThat(pushSubjectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
