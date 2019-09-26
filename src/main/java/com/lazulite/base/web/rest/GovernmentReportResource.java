package com.lazulite.base.web.rest;

import com.lazulite.base.domain.GovernmentReport;
import com.lazulite.base.repository.GovernmentReportRepository;
import com.lazulite.base.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lazulite.base.domain.GovernmentReport}.
 */
@RestController
@RequestMapping("/api")
public class GovernmentReportResource {

    private final Logger log = LoggerFactory.getLogger(GovernmentReportResource.class);

    private static final String ENTITY_NAME = "governmentReport";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GovernmentReportRepository governmentReportRepository;

    public GovernmentReportResource(GovernmentReportRepository governmentReportRepository) {
        this.governmentReportRepository = governmentReportRepository;
    }

    /**
     * {@code POST  /government-reports} : Create a new governmentReport.
     *
     * @param governmentReport the governmentReport to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new governmentReport, or with status {@code 400 (Bad Request)} if the governmentReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/government-reports")
    public ResponseEntity<GovernmentReport> createGovernmentReport(@RequestBody GovernmentReport governmentReport) throws URISyntaxException {
        log.debug("REST request to save GovernmentReport : {}", governmentReport);
        if (governmentReport.getId() != null) {
            throw new BadRequestAlertException("A new governmentReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GovernmentReport result = governmentReportRepository.save(governmentReport);
        return ResponseEntity.created(new URI("/api/government-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /government-reports} : Updates an existing governmentReport.
     *
     * @param governmentReport the governmentReport to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated governmentReport,
     * or with status {@code 400 (Bad Request)} if the governmentReport is not valid,
     * or with status {@code 500 (Internal Server Error)} if the governmentReport couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/government-reports")
    public ResponseEntity<GovernmentReport> updateGovernmentReport(@RequestBody GovernmentReport governmentReport) throws URISyntaxException {
        log.debug("REST request to update GovernmentReport : {}", governmentReport);
        if (governmentReport.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GovernmentReport result = governmentReportRepository.save(governmentReport);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, governmentReport.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /government-reports} : get all the governmentReports.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of governmentReports in body.
     */
    @GetMapping("/government-reports")
    public List<GovernmentReport> getAllGovernmentReports() {
        log.debug("REST request to get all GovernmentReports");
        return governmentReportRepository.findAll();
    }

    /**
     * {@code GET  /government-reports/:id} : get the "id" governmentReport.
     *
     * @param id the id of the governmentReport to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the governmentReport, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/government-reports/{id}")
    public ResponseEntity<GovernmentReport> getGovernmentReport(@PathVariable Long id) {
        log.debug("REST request to get GovernmentReport : {}", id);
        Optional<GovernmentReport> governmentReport = governmentReportRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(governmentReport);
    }

    /**
     * {@code DELETE  /government-reports/:id} : delete the "id" governmentReport.
     *
     * @param id the id of the governmentReport to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/government-reports/{id}")
    public ResponseEntity<Void> deleteGovernmentReport(@PathVariable Long id) {
        log.debug("REST request to delete GovernmentReport : {}", id);
        governmentReportRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
