package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.MsgReceiverGroup;
import com.lazulite.base.repository.MsgReceiverGroupRepository;
import com.lazulite.base.service.MsgReceiverGroupService;

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
 * Integration tests for the {@link MsgReceiverGroupResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MsgReceiverGroupResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_DESC = "BBBBBBBBBB";

    @Autowired
    private MsgReceiverGroupRepository msgReceiverGroupRepository;

    @Autowired
    private MsgReceiverGroupService msgReceiverGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMsgReceiverGroupMockMvc;

    private MsgReceiverGroup msgReceiverGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgReceiverGroup createEntity(EntityManager em) {
        MsgReceiverGroup msgReceiverGroup = new MsgReceiverGroup()
            .name(DEFAULT_NAME)
            .desc(DEFAULT_DESC);
        return msgReceiverGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MsgReceiverGroup createUpdatedEntity(EntityManager em) {
        MsgReceiverGroup msgReceiverGroup = new MsgReceiverGroup()
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);
        return msgReceiverGroup;
    }

    @BeforeEach
    public void initTest() {
        msgReceiverGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createMsgReceiverGroup() throws Exception {
        int databaseSizeBeforeCreate = msgReceiverGroupRepository.findAll().size();

        // Create the MsgReceiverGroup
        restMsgReceiverGroupMockMvc.perform(post("/api/msg-receiver-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgReceiverGroup)))
            .andExpect(status().isCreated());

        // Validate the MsgReceiverGroup in the database
        List<MsgReceiverGroup> msgReceiverGroupList = msgReceiverGroupRepository.findAll();
        assertThat(msgReceiverGroupList).hasSize(databaseSizeBeforeCreate + 1);
        MsgReceiverGroup testMsgReceiverGroup = msgReceiverGroupList.get(msgReceiverGroupList.size() - 1);
        assertThat(testMsgReceiverGroup.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMsgReceiverGroup.getDesc()).isEqualTo(DEFAULT_DESC);
    }

    @Test
    @Transactional
    public void createMsgReceiverGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = msgReceiverGroupRepository.findAll().size();

        // Create the MsgReceiverGroup with an existing ID
        msgReceiverGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMsgReceiverGroupMockMvc.perform(post("/api/msg-receiver-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgReceiverGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MsgReceiverGroup in the database
        List<MsgReceiverGroup> msgReceiverGroupList = msgReceiverGroupRepository.findAll();
        assertThat(msgReceiverGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMsgReceiverGroups() throws Exception {
        // Initialize the database
        msgReceiverGroupRepository.saveAndFlush(msgReceiverGroup);

        // Get all the msgReceiverGroupList
        restMsgReceiverGroupMockMvc.perform(get("/api/msg-receiver-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(msgReceiverGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC)));
    }
    
    @Test
    @Transactional
    public void getMsgReceiverGroup() throws Exception {
        // Initialize the database
        msgReceiverGroupRepository.saveAndFlush(msgReceiverGroup);

        // Get the msgReceiverGroup
        restMsgReceiverGroupMockMvc.perform(get("/api/msg-receiver-groups/{id}", msgReceiverGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(msgReceiverGroup.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.desc").value(DEFAULT_DESC));
    }

    @Test
    @Transactional
    public void getNonExistingMsgReceiverGroup() throws Exception {
        // Get the msgReceiverGroup
        restMsgReceiverGroupMockMvc.perform(get("/api/msg-receiver-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMsgReceiverGroup() throws Exception {
        // Initialize the database
        msgReceiverGroupService.save(msgReceiverGroup);

        int databaseSizeBeforeUpdate = msgReceiverGroupRepository.findAll().size();

        // Update the msgReceiverGroup
        MsgReceiverGroup updatedMsgReceiverGroup = msgReceiverGroupRepository.findById(msgReceiverGroup.getId()).get();
        // Disconnect from session so that the updates on updatedMsgReceiverGroup are not directly saved in db
        em.detach(updatedMsgReceiverGroup);
        updatedMsgReceiverGroup
            .name(UPDATED_NAME)
            .desc(UPDATED_DESC);

        restMsgReceiverGroupMockMvc.perform(put("/api/msg-receiver-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMsgReceiverGroup)))
            .andExpect(status().isOk());

        // Validate the MsgReceiverGroup in the database
        List<MsgReceiverGroup> msgReceiverGroupList = msgReceiverGroupRepository.findAll();
        assertThat(msgReceiverGroupList).hasSize(databaseSizeBeforeUpdate);
        MsgReceiverGroup testMsgReceiverGroup = msgReceiverGroupList.get(msgReceiverGroupList.size() - 1);
        assertThat(testMsgReceiverGroup.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMsgReceiverGroup.getDesc()).isEqualTo(UPDATED_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingMsgReceiverGroup() throws Exception {
        int databaseSizeBeforeUpdate = msgReceiverGroupRepository.findAll().size();

        // Create the MsgReceiverGroup

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMsgReceiverGroupMockMvc.perform(put("/api/msg-receiver-groups")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(msgReceiverGroup)))
            .andExpect(status().isBadRequest());

        // Validate the MsgReceiverGroup in the database
        List<MsgReceiverGroup> msgReceiverGroupList = msgReceiverGroupRepository.findAll();
        assertThat(msgReceiverGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMsgReceiverGroup() throws Exception {
        // Initialize the database
        msgReceiverGroupService.save(msgReceiverGroup);

        int databaseSizeBeforeDelete = msgReceiverGroupRepository.findAll().size();

        // Delete the msgReceiverGroup
        restMsgReceiverGroupMockMvc.perform(delete("/api/msg-receiver-groups/{id}", msgReceiverGroup.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MsgReceiverGroup> msgReceiverGroupList = msgReceiverGroupRepository.findAll();
        assertThat(msgReceiverGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
