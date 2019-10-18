package com.lazulite.base.service;

import com.lazulite.base.domain.HistorySearch;
import com.lazulite.base.repository.HistorySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistorySearch}.
 */
@Service
@Transactional
public class HistorySearchService {

    private final Logger log = LoggerFactory.getLogger(HistorySearchService.class);

    private final HistorySearchRepository historySearchRepository;

    public HistorySearchService(HistorySearchRepository historySearchRepository) {
        this.historySearchRepository = historySearchRepository;
    }

    /**
     * Save a historySearch.
     *
     * @param historySearch the entity to save.
     * @return the persisted entity.
     */
    public HistorySearch save(HistorySearch historySearch) {
        log.debug("Request to save HistorySearch : {}", historySearch);
        return historySearchRepository.save(historySearch);
    }

    /**
     * Get all the historySearches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<HistorySearch> findAll(Pageable pageable) {
        log.debug("Request to get all HistorySearches");
        return historySearchRepository.findAll(pageable);
    }


    /**
     * Get one historySearch by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<HistorySearch> findOne(Long id) {
        log.debug("Request to get HistorySearch : {}", id);
        return historySearchRepository.findById(id);
    }

    /**
     * Delete the historySearch by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete HistorySearch : {}", id);
        historySearchRepository.deleteById(id);
    }
}
