package com.lazulite.base.web.rest;

import com.lazulite.base.domain.FmpMicroApp;
import com.lazulite.base.service.FmpMicroAppService;
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
 * REST controller for managing {@link com.lazulite.base.domain.FmpMicroApp}.
 */
@RestController
@RequestMapping("/api")
public class FmpMicroAppResource {

    private final Logger log = LoggerFactory.getLogger(FmpMicroAppResource.class);

    private static final String ENTITY_NAME = "fmpMicroApp";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FmpMicroAppService fmpMicroAppService;

    public FmpMicroAppResource(FmpMicroAppService fmpMicroAppService) {
        this.fmpMicroAppService = fmpMicroAppService;
    }

    /**
     * {@code POST  /fmp-micro-apps} : Create a new fmpMicroApp.
     *
     * @param fmpMicroApp the fmpMicroApp to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fmpMicroApp, or with status {@code 400 (Bad Request)} if the fmpMicroApp has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fmp-micro-apps")
    public ResponseEntity<FmpMicroApp> createFmpMicroApp(@RequestBody FmpMicroApp fmpMicroApp) throws URISyntaxException {
        log.debug("REST request to save FmpMicroApp : {}", fmpMicroApp);
        if (fmpMicroApp.getId() != null) {
            throw new BadRequestAlertException("A new fmpMicroApp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FmpMicroApp result = fmpMicroAppService.save(fmpMicroApp);
        return ResponseEntity.created(new URI("/api/fmp-micro-apps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fmp-micro-apps} : Updates an existing fmpMicroApp.
     *
     * @param fmpMicroApp the fmpMicroApp to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fmpMicroApp,
     * or with status {@code 400 (Bad Request)} if the fmpMicroApp is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fmpMicroApp couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fmp-micro-apps")
    public ResponseEntity<FmpMicroApp> updateFmpMicroApp(@RequestBody FmpMicroApp fmpMicroApp) throws URISyntaxException {
        log.debug("REST request to update FmpMicroApp : {}", fmpMicroApp);
        if (fmpMicroApp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FmpMicroApp result = fmpMicroAppService.save(fmpMicroApp);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fmpMicroApp.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fmp-micro-apps} : get all the fmpMicroApps.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fmpMicroApps in body.
     */
    @GetMapping("/fmp-micro-apps")
    public List<FmpMicroApp> getAllFmpMicroApps(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all FmpMicroApps");
        return fmpMicroAppService.findAll();
    }

    /**
     * {@code GET  /fmp-micro-apps/:id} : get the "id" fmpMicroApp.
     *
     * @param id the id of the fmpMicroApp to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fmpMicroApp, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fmp-micro-apps/{id}")
    public ResponseEntity<FmpMicroApp> getFmpMicroApp(@PathVariable Long id) {
        log.debug("REST request to get FmpMicroApp : {}", id);
        Optional<FmpMicroApp> fmpMicroApp = fmpMicroAppService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fmpMicroApp);
    }

    /**
     * {@code DELETE  /fmp-micro-apps/:id} : delete the "id" fmpMicroApp.
     *
     * @param id the id of the fmpMicroApp to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fmp-micro-apps/{id}")
    public ResponseEntity<Void> deleteFmpMicroApp(@PathVariable Long id) {
        log.debug("REST request to delete FmpMicroApp : {}", id);
        fmpMicroAppService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
