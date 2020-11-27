package com.lazulite.base.web.rest;

import com.lazulite.base.domain.MicroAppGroup;
import com.lazulite.base.service.MicroAppGroupService;
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
 * REST controller for managing {@link com.lazulite.base.domain.MicroAppGroup}.
 */
@RestController
@RequestMapping("/api")
public class MicroAppGroupResource {

    private final Logger log = LoggerFactory.getLogger(MicroAppGroupResource.class);

    private static final String ENTITY_NAME = "microAppGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MicroAppGroupService microAppGroupService;

    public MicroAppGroupResource(MicroAppGroupService microAppGroupService) {
        this.microAppGroupService = microAppGroupService;
    }

    /**
     * {@code POST  /micro-app-groups} : Create a new microAppGroup.
     *
     * @param microAppGroup the microAppGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new microAppGroup, or with status {@code 400 (Bad Request)} if the microAppGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/micro-app-groups")
    public ResponseEntity<MicroAppGroup> createMicroAppGroup(@RequestBody MicroAppGroup microAppGroup) throws URISyntaxException {
        log.debug("REST request to save MicroAppGroup : {}", microAppGroup);
        if (microAppGroup.getId() != null) {
            throw new BadRequestAlertException("A new microAppGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MicroAppGroup result = microAppGroupService.save(microAppGroup);
        return ResponseEntity.created(new URI("/api/micro-app-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /micro-app-groups} : Updates an existing microAppGroup.
     *
     * @param microAppGroup the microAppGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated microAppGroup,
     * or with status {@code 400 (Bad Request)} if the microAppGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the microAppGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/micro-app-groups")
    public ResponseEntity<MicroAppGroup> updateMicroAppGroup(@RequestBody MicroAppGroup microAppGroup) throws URISyntaxException {
        log.debug("REST request to update MicroAppGroup : {}", microAppGroup);
        if (microAppGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MicroAppGroup result = microAppGroupService.save(microAppGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, microAppGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /micro-app-groups} : get all the microAppGroups.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of microAppGroups in body.
     */
    @GetMapping("/micro-app-groups")
    public List<MicroAppGroup> getAllMicroAppGroups(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MicroAppGroups");
        return microAppGroupService.findAll();
    }

    /**
     * {@code GET  /micro-app-groups/:id} : get the "id" microAppGroup.
     *
     * @param id the id of the microAppGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the microAppGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/micro-app-groups/{id}")
    public ResponseEntity<MicroAppGroup> getMicroAppGroup(@PathVariable Long id) {
        log.debug("REST request to get MicroAppGroup : {}", id);
        Optional<MicroAppGroup> microAppGroup = microAppGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(microAppGroup);
    }

    /**
     * {@code DELETE  /micro-app-groups/:id} : delete the "id" microAppGroup.
     *
     * @param id the id of the microAppGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/micro-app-groups/{id}")
    public ResponseEntity<Void> deleteMicroAppGroup(@PathVariable Long id) {
        log.debug("REST request to delete MicroAppGroup : {}", id);
        microAppGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
