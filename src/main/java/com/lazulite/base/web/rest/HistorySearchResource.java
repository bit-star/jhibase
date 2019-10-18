package com.lazulite.base.web.rest;

import com.lazulite.base.domain.HistorySearch;
import com.lazulite.base.service.HistorySearchService;
import com.lazulite.base.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lazulite.base.domain.HistorySearch}.
 */
@RestController
@RequestMapping("/api")
public class HistorySearchResource {

    private final Logger log = LoggerFactory.getLogger(HistorySearchResource.class);

    private static final String ENTITY_NAME = "historySearch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistorySearchService historySearchService;

    public HistorySearchResource(HistorySearchService historySearchService) {
        this.historySearchService = historySearchService;
    }

    /**
     * {@code POST  /history-searches} : Create a new historySearch.
     *
     * @param historySearch the historySearch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historySearch, or with status {@code 400 (Bad Request)} if the historySearch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/history-searches")
    public ResponseEntity<HistorySearch> createHistorySearch(@RequestBody HistorySearch historySearch) throws URISyntaxException {
        log.debug("REST request to save HistorySearch : {}", historySearch);
        if (historySearch.getId() != null) {
            throw new BadRequestAlertException("A new historySearch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistorySearch result = historySearchService.save(historySearch);
        return ResponseEntity.created(new URI("/api/history-searches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /history-searches} : Updates an existing historySearch.
     *
     * @param historySearch the historySearch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historySearch,
     * or with status {@code 400 (Bad Request)} if the historySearch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historySearch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/history-searches")
    public ResponseEntity<HistorySearch> updateHistorySearch(@RequestBody HistorySearch historySearch) throws URISyntaxException {
        log.debug("REST request to update HistorySearch : {}", historySearch);
        if (historySearch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistorySearch result = historySearchService.save(historySearch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historySearch.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /history-searches} : get all the historySearches.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historySearches in body.
     */
    @GetMapping("/history-searches")
    public ResponseEntity<List<HistorySearch>> getAllHistorySearches(Pageable pageable) {
        log.debug("REST request to get a page of HistorySearches");
        Page<HistorySearch> page = historySearchService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /history-searches/:id} : get the "id" historySearch.
     *
     * @param id the id of the historySearch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historySearch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/history-searches/{id}")
    public ResponseEntity<HistorySearch> getHistorySearch(@PathVariable Long id) {
        log.debug("REST request to get HistorySearch : {}", id);
        Optional<HistorySearch> historySearch = historySearchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historySearch);
    }

    /**
     * {@code DELETE  /history-searches/:id} : delete the "id" historySearch.
     *
     * @param id the id of the historySearch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/history-searches/{id}")
    public ResponseEntity<Void> deleteHistorySearch(@PathVariable Long id) {
        log.debug("REST request to delete HistorySearch : {}", id);
        historySearchService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
