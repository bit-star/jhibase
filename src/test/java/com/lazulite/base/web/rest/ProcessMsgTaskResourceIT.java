package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.ProcessMsgTask;
import com.lazulite.base.repository.ProcessMsgTaskRepository;
import com.lazulite.base.service.ProcessMsgTaskService;

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
 * Integration tests for the {@link ProcessMsgTaskResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ProcessMsgTaskResourceIT {

    @Autowired
    private ProcessMsgTaskRepository processMsgTaskRepository;

    @Autowired
    private ProcessMsgTaskService processMsgTaskService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProcessMsgTaskMockMvc;

    private ProcessMsgTask processMsgTask;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcessMsgTask createEntity(EntityManager em) {
        ProcessMsgTask processMsgTask = new ProcessMsgTask();
        return processMsgTask;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProcessMsgTask createUpdatedEntity(EntityManager em) {
        ProcessMsgTask processMsgTask = new ProcessMsgTask();
        return processMsgTask;
    }

    @BeforeEach
    public void initTest() {
        processMsgTask = createEntity(em);
    }

    @Test
    @Transactional
    public void createProcessMsgTask() throws Exception {
        int databaseSizeBeforeCreate = processMsgTaskRepository.findAll().size();

        // Create the ProcessMsgTask
        restProcessMsgTaskMockMvc.perform(post("/api/process-msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(processMsgTask)))
            .andExpect(status().isCreated());

        // Validate the ProcessMsgTask in the database
        List<ProcessMsgTask> processMsgTaskList = processMsgTaskRepository.findAll();
        assertThat(processMsgTaskList).hasSize(databaseSizeBeforeCreate + 1);
        ProcessMsgTask testProcessMsgTask = processMsgTaskList.get(processMsgTaskList.size() - 1);
    }

    @Test
    @Transactional
    public void createProcessMsgTaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = processMsgTaskRepository.findAll().size();

        // Create the ProcessMsgTask with an existing ID
        processMsgTask.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcessMsgTaskMockMvc.perform(post("/api/process-msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(processMsgTask)))
            .andExpect(status().isBadRequest());

        // Validate the ProcessMsgTask in the database
        List<ProcessMsgTask> processMsgTaskList = processMsgTaskRepository.findAll();
        assertThat(processMsgTaskList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllProcessMsgTasks() throws Exception {
        // Initialize the database
        processMsgTaskRepository.saveAndFlush(processMsgTask);

        // Get all the processMsgTaskList
        restProcessMsgTaskMockMvc.perform(get("/api/process-msg-tasks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(processMsgTask.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getProcessMsgTask() throws Exception {
        // Initialize the database
        processMsgTaskRepository.saveAndFlush(processMsgTask);

        // Get the processMsgTask
        restProcessMsgTaskMockMvc.perform(get("/api/process-msg-tasks/{id}", processMsgTask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(processMsgTask.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProcessMsgTask() throws Exception {
        // Get the processMsgTask
        restProcessMsgTaskMockMvc.perform(get("/api/process-msg-tasks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProcessMsgTask() throws Exception {
        // Initialize the database
        processMsgTaskService.save(processMsgTask);

        int databaseSizeBeforeUpdate = processMsgTaskRepository.findAll().size();

        // Update the processMsgTask
        ProcessMsgTask updatedProcessMsgTask = processMsgTaskRepository.findById(processMsgTask.getId()).get();
        // Disconnect from session so that the updates on updatedProcessMsgTask are not directly saved in db
        em.detach(updatedProcessMsgTask);

        restProcessMsgTaskMockMvc.perform(put("/api/process-msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProcessMsgTask)))
            .andExpect(status().isOk());

        // Validate the ProcessMsgTask in the database
        List<ProcessMsgTask> processMsgTaskList = processMsgTaskRepository.findAll();
        assertThat(processMsgTaskList).hasSize(databaseSizeBeforeUpdate);
        ProcessMsgTask testProcessMsgTask = processMsgTaskList.get(processMsgTaskList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingProcessMsgTask() throws Exception {
        int databaseSizeBeforeUpdate = processMsgTaskRepository.findAll().size();

        // Create the ProcessMsgTask

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcessMsgTaskMockMvc.perform(put("/api/process-msg-tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(processMsgTask)))
            .andExpect(status().isBadRequest());

        // Validate the ProcessMsgTask in the database
        List<ProcessMsgTask> processMsgTaskList = processMsgTaskRepository.findAll();
        assertThat(processMsgTaskList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProcessMsgTask() throws Exception {
        // Initialize the database
        processMsgTaskService.save(processMsgTask);

        int databaseSizeBeforeDelete = processMsgTaskRepository.findAll().size();

        // Delete the processMsgTask
        restProcessMsgTaskMockMvc.perform(delete("/api/process-msg-tasks/{id}", processMsgTask.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProcessMsgTask> processMsgTaskList = processMsgTaskRepository.findAll();
        assertThat(processMsgTaskList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
