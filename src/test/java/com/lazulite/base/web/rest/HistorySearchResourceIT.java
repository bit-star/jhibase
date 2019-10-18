package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.HistorySearch;
import com.lazulite.base.repository.HistorySearchRepository;
import com.lazulite.base.service.HistorySearchService;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HistorySearchResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class HistorySearchResourceIT {

    private static final String DEFAULT_SEARCH_CONETNT = "AAAAAAAAAA";
    private static final String UPDATED_SEARCH_CONETNT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SEARCH_COUNT = 1;
    private static final Integer UPDATED_SEARCH_COUNT = 2;

    private static final Integer DEFAULT_IS_HOT = 1;
    private static final Integer UPDATED_IS_HOT = 2;

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private HistorySearchRepository historySearchRepository;

    @Autowired
    private HistorySearchService historySearchService;

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

    private MockMvc restHistorySearchMockMvc;

    private HistorySearch historySearch;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistorySearchResource historySearchResource = new HistorySearchResource(historySearchService);
        this.restHistorySearchMockMvc = MockMvcBuilders.standaloneSetup(historySearchResource)
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
    public static HistorySearch createEntity(EntityManager em) {
        HistorySearch historySearch = new HistorySearch()
            .searchConetnt(DEFAULT_SEARCH_CONETNT)
            .searchCount(DEFAULT_SEARCH_COUNT)
            .isHot(DEFAULT_IS_HOT)
            .createdDate(DEFAULT_CREATED_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return historySearch;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistorySearch createUpdatedEntity(EntityManager em) {
        HistorySearch historySearch = new HistorySearch()
            .searchConetnt(UPDATED_SEARCH_CONETNT)
            .searchCount(UPDATED_SEARCH_COUNT)
            .isHot(UPDATED_IS_HOT)
            .createdDate(UPDATED_CREATED_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        return historySearch;
    }

    @BeforeEach
    public void initTest() {
        historySearch = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistorySearch() throws Exception {
        int databaseSizeBeforeCreate = historySearchRepository.findAll().size();

        // Create the HistorySearch
        restHistorySearchMockMvc.perform(post("/api/history-searches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historySearch)))
            .andExpect(status().isCreated());

        // Validate the HistorySearch in the database
        List<HistorySearch> historySearchList = historySearchRepository.findAll();
        assertThat(historySearchList).hasSize(databaseSizeBeforeCreate + 1);
        HistorySearch testHistorySearch = historySearchList.get(historySearchList.size() - 1);
        assertThat(testHistorySearch.getSearchConetnt()).isEqualTo(DEFAULT_SEARCH_CONETNT);
        assertThat(testHistorySearch.getSearchCount()).isEqualTo(DEFAULT_SEARCH_COUNT);
        assertThat(testHistorySearch.getIsHot()).isEqualTo(DEFAULT_IS_HOT);
        assertThat(testHistorySearch.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testHistorySearch.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createHistorySearchWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historySearchRepository.findAll().size();

        // Create the HistorySearch with an existing ID
        historySearch.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistorySearchMockMvc.perform(post("/api/history-searches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historySearch)))
            .andExpect(status().isBadRequest());

        // Validate the HistorySearch in the database
        List<HistorySearch> historySearchList = historySearchRepository.findAll();
        assertThat(historySearchList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHistorySearches() throws Exception {
        // Initialize the database
        historySearchRepository.saveAndFlush(historySearch);

        // Get all the historySearchList
        restHistorySearchMockMvc.perform(get("/api/history-searches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historySearch.getId().intValue())))
            .andExpect(jsonPath("$.[*].searchConetnt").value(hasItem(DEFAULT_SEARCH_CONETNT)))
            .andExpect(jsonPath("$.[*].searchCount").value(hasItem(DEFAULT_SEARCH_COUNT)))
            .andExpect(jsonPath("$.[*].isHot").value(hasItem(DEFAULT_IS_HOT)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getHistorySearch() throws Exception {
        // Initialize the database
        historySearchRepository.saveAndFlush(historySearch);

        // Get the historySearch
        restHistorySearchMockMvc.perform(get("/api/history-searches/{id}", historySearch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historySearch.getId().intValue()))
            .andExpect(jsonPath("$.searchConetnt").value(DEFAULT_SEARCH_CONETNT))
            .andExpect(jsonPath("$.searchCount").value(DEFAULT_SEARCH_COUNT))
            .andExpect(jsonPath("$.isHot").value(DEFAULT_IS_HOT))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistorySearch() throws Exception {
        // Get the historySearch
        restHistorySearchMockMvc.perform(get("/api/history-searches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistorySearch() throws Exception {
        // Initialize the database
        historySearchService.save(historySearch);

        int databaseSizeBeforeUpdate = historySearchRepository.findAll().size();

        // Update the historySearch
        HistorySearch updatedHistorySearch = historySearchRepository.findById(historySearch.getId()).get();
        // Disconnect from session so that the updates on updatedHistorySearch are not directly saved in db
        em.detach(updatedHistorySearch);
        updatedHistorySearch
            .searchConetnt(UPDATED_SEARCH_CONETNT)
            .searchCount(UPDATED_SEARCH_COUNT)
            .isHot(UPDATED_IS_HOT)
            .createdDate(UPDATED_CREATED_DATE)
            .updateDate(UPDATED_UPDATE_DATE);

        restHistorySearchMockMvc.perform(put("/api/history-searches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHistorySearch)))
            .andExpect(status().isOk());

        // Validate the HistorySearch in the database
        List<HistorySearch> historySearchList = historySearchRepository.findAll();
        assertThat(historySearchList).hasSize(databaseSizeBeforeUpdate);
        HistorySearch testHistorySearch = historySearchList.get(historySearchList.size() - 1);
        assertThat(testHistorySearch.getSearchConetnt()).isEqualTo(UPDATED_SEARCH_CONETNT);
        assertThat(testHistorySearch.getSearchCount()).isEqualTo(UPDATED_SEARCH_COUNT);
        assertThat(testHistorySearch.getIsHot()).isEqualTo(UPDATED_IS_HOT);
        assertThat(testHistorySearch.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testHistorySearch.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingHistorySearch() throws Exception {
        int databaseSizeBeforeUpdate = historySearchRepository.findAll().size();

        // Create the HistorySearch

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistorySearchMockMvc.perform(put("/api/history-searches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historySearch)))
            .andExpect(status().isBadRequest());

        // Validate the HistorySearch in the database
        List<HistorySearch> historySearchList = historySearchRepository.findAll();
        assertThat(historySearchList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistorySearch() throws Exception {
        // Initialize the database
        historySearchService.save(historySearch);

        int databaseSizeBeforeDelete = historySearchRepository.findAll().size();

        // Delete the historySearch
        restHistorySearchMockMvc.perform(delete("/api/history-searches/{id}", historySearch.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistorySearch> historySearchList = historySearchRepository.findAll();
        assertThat(historySearchList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistorySearch.class);
        HistorySearch historySearch1 = new HistorySearch();
        historySearch1.setId(1L);
        HistorySearch historySearch2 = new HistorySearch();
        historySearch2.setId(historySearch1.getId());
        assertThat(historySearch1).isEqualTo(historySearch2);
        historySearch2.setId(2L);
        assertThat(historySearch1).isNotEqualTo(historySearch2);
        historySearch1.setId(null);
        assertThat(historySearch1).isNotEqualTo(historySearch2);
    }
}
