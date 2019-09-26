package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.CspaceFile;
import com.lazulite.base.repository.CspaceFileRepository;
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
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CspaceFileResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class CspaceFileResourceIT {

    private static final String DEFAULT_SPACE_ID = "AAAAAAAAAA";
    private static final String UPDATED_SPACE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_ID = "AAAAAAAAAA";
    private static final String UPDATED_FILE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_FILE_SIZE = 1L;
    private static final Long UPDATED_FILE_SIZE = 2L;
    private static final Long SMALLER_FILE_SIZE = 1L - 1L;

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private CspaceFileRepository cspaceFileRepository;

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

    private MockMvc restCspaceFileMockMvc;

    private CspaceFile cspaceFile;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CspaceFileResource cspaceFileResource = new CspaceFileResource(cspaceFileRepository);
        this.restCspaceFileMockMvc = MockMvcBuilders.standaloneSetup(cspaceFileResource)
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
    public static CspaceFile createEntity(EntityManager em) {
        CspaceFile cspaceFile = new CspaceFile()
            .spaceId(DEFAULT_SPACE_ID)
            .fileId(DEFAULT_FILE_ID)
            .fileName(DEFAULT_FILE_NAME)
            .fileSize(DEFAULT_FILE_SIZE)
            .fileType(DEFAULT_FILE_TYPE);
        return cspaceFile;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CspaceFile createUpdatedEntity(EntityManager em) {
        CspaceFile cspaceFile = new CspaceFile()
            .spaceId(UPDATED_SPACE_ID)
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .fileSize(UPDATED_FILE_SIZE)
            .fileType(UPDATED_FILE_TYPE);
        return cspaceFile;
    }

    @BeforeEach
    public void initTest() {
        cspaceFile = createEntity(em);
    }

    @Test
    @Transactional
    public void createCspaceFile() throws Exception {
        int databaseSizeBeforeCreate = cspaceFileRepository.findAll().size();

        // Create the CspaceFile
        restCspaceFileMockMvc.perform(post("/api/cspace-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cspaceFile)))
            .andExpect(status().isCreated());

        // Validate the CspaceFile in the database
        List<CspaceFile> cspaceFileList = cspaceFileRepository.findAll();
        assertThat(cspaceFileList).hasSize(databaseSizeBeforeCreate + 1);
        CspaceFile testCspaceFile = cspaceFileList.get(cspaceFileList.size() - 1);
        assertThat(testCspaceFile.getSpaceId()).isEqualTo(DEFAULT_SPACE_ID);
        assertThat(testCspaceFile.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testCspaceFile.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testCspaceFile.getFileSize()).isEqualTo(DEFAULT_FILE_SIZE);
        assertThat(testCspaceFile.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createCspaceFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cspaceFileRepository.findAll().size();

        // Create the CspaceFile with an existing ID
        cspaceFile.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCspaceFileMockMvc.perform(post("/api/cspace-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cspaceFile)))
            .andExpect(status().isBadRequest());

        // Validate the CspaceFile in the database
        List<CspaceFile> cspaceFileList = cspaceFileRepository.findAll();
        assertThat(cspaceFileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCspaceFiles() throws Exception {
        // Initialize the database
        cspaceFileRepository.saveAndFlush(cspaceFile);

        // Get all the cspaceFileList
        restCspaceFileMockMvc.perform(get("/api/cspace-files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cspaceFile.getId().intValue())))
            .andExpect(jsonPath("$.[*].spaceId").value(hasItem(DEFAULT_SPACE_ID.toString())))
            .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fileSize").value(hasItem(DEFAULT_FILE_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getCspaceFile() throws Exception {
        // Initialize the database
        cspaceFileRepository.saveAndFlush(cspaceFile);

        // Get the cspaceFile
        restCspaceFileMockMvc.perform(get("/api/cspace-files/{id}", cspaceFile.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cspaceFile.getId().intValue()))
            .andExpect(jsonPath("$.spaceId").value(DEFAULT_SPACE_ID.toString()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.fileSize").value(DEFAULT_FILE_SIZE.intValue()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCspaceFile() throws Exception {
        // Get the cspaceFile
        restCspaceFileMockMvc.perform(get("/api/cspace-files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCspaceFile() throws Exception {
        // Initialize the database
        cspaceFileRepository.saveAndFlush(cspaceFile);

        int databaseSizeBeforeUpdate = cspaceFileRepository.findAll().size();

        // Update the cspaceFile
        CspaceFile updatedCspaceFile = cspaceFileRepository.findById(cspaceFile.getId()).get();
        // Disconnect from session so that the updates on updatedCspaceFile are not directly saved in db
        em.detach(updatedCspaceFile);
        updatedCspaceFile
            .spaceId(UPDATED_SPACE_ID)
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .fileSize(UPDATED_FILE_SIZE)
            .fileType(UPDATED_FILE_TYPE);

        restCspaceFileMockMvc.perform(put("/api/cspace-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCspaceFile)))
            .andExpect(status().isOk());

        // Validate the CspaceFile in the database
        List<CspaceFile> cspaceFileList = cspaceFileRepository.findAll();
        assertThat(cspaceFileList).hasSize(databaseSizeBeforeUpdate);
        CspaceFile testCspaceFile = cspaceFileList.get(cspaceFileList.size() - 1);
        assertThat(testCspaceFile.getSpaceId()).isEqualTo(UPDATED_SPACE_ID);
        assertThat(testCspaceFile.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testCspaceFile.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testCspaceFile.getFileSize()).isEqualTo(UPDATED_FILE_SIZE);
        assertThat(testCspaceFile.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCspaceFile() throws Exception {
        int databaseSizeBeforeUpdate = cspaceFileRepository.findAll().size();

        // Create the CspaceFile

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCspaceFileMockMvc.perform(put("/api/cspace-files")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cspaceFile)))
            .andExpect(status().isBadRequest());

        // Validate the CspaceFile in the database
        List<CspaceFile> cspaceFileList = cspaceFileRepository.findAll();
        assertThat(cspaceFileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCspaceFile() throws Exception {
        // Initialize the database
        cspaceFileRepository.saveAndFlush(cspaceFile);

        int databaseSizeBeforeDelete = cspaceFileRepository.findAll().size();

        // Delete the cspaceFile
        restCspaceFileMockMvc.perform(delete("/api/cspace-files/{id}", cspaceFile.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CspaceFile> cspaceFileList = cspaceFileRepository.findAll();
        assertThat(cspaceFileList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CspaceFile.class);
        CspaceFile cspaceFile1 = new CspaceFile();
        cspaceFile1.setId(1L);
        CspaceFile cspaceFile2 = new CspaceFile();
        cspaceFile2.setId(cspaceFile1.getId());
        assertThat(cspaceFile1).isEqualTo(cspaceFile2);
        cspaceFile2.setId(2L);
        assertThat(cspaceFile1).isNotEqualTo(cspaceFile2);
        cspaceFile1.setId(null);
        assertThat(cspaceFile1).isNotEqualTo(cspaceFile2);
    }
}
