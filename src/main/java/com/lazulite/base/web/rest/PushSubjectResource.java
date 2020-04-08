package com.lazulite.base.web.rest;

import com.lazulite.base.domain.PushSubject;
import com.lazulite.base.service.PushSubjectService;
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
 * REST controller for managing {@link com.lazulite.base.domain.PushSubject}.
 */
@RestController
@RequestMapping("/api")
public class PushSubjectResource {

    private final Logger log = LoggerFactory.getLogger(PushSubjectResource.class);

    private static final String ENTITY_NAME = "pushSubject";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PushSubjectService pushSubjectService;

    public PushSubjectResource(PushSubjectService pushSubjectService) {
        this.pushSubjectService = pushSubjectService;
    }

    /**
     * {@code POST  /push-subjects} : Create a new pushSubject.
     *
     * @param pushSubject the pushSubject to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pushSubject, or with status {@code 400 (Bad Request)} if the pushSubject has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/push-subjects")
    public ResponseEntity<PushSubject> createPushSubject(@RequestBody PushSubject pushSubject) throws URISyntaxException {
        log.debug("REST request to save PushSubject : {}", pushSubject);
        if (pushSubject.getId() != null) {
            throw new BadRequestAlertException("A new pushSubject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PushSubject result = pushSubjectService.save(pushSubject);
        return ResponseEntity.created(new URI("/api/push-subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /push-subjects} : Updates an existing pushSubject.
     *
     * @param pushSubject the pushSubject to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pushSubject,
     * or with status {@code 400 (Bad Request)} if the pushSubject is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pushSubject couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/push-subjects")
    public ResponseEntity<PushSubject> updatePushSubject(@RequestBody PushSubject pushSubject) throws URISyntaxException {
        log.debug("REST request to update PushSubject : {}", pushSubject);
        if (pushSubject.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PushSubject result = pushSubjectService.save(pushSubject);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pushSubject.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /push-subjects} : get all the pushSubjects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pushSubjects in body.
     */
    @GetMapping("/push-subjects")
    public List<PushSubject> getAllPushSubjects() {
        log.debug("REST request to get all PushSubjects");
        return pushSubjectService.findAll();
    }

    /**
     * {@code GET  /push-subjects/:id} : get the "id" pushSubject.
     *
     * @param id the id of the pushSubject to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pushSubject, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/push-subjects/{id}")
    public ResponseEntity<PushSubject> getPushSubject(@PathVariable Long id) {
        log.debug("REST request to get PushSubject : {}", id);
        Optional<PushSubject> pushSubject = pushSubjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pushSubject);
    }

    /**
     * {@code DELETE  /push-subjects/:id} : delete the "id" pushSubject.
     *
     * @param id the id of the pushSubject to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/push-subjects/{id}")
    public ResponseEntity<Void> deletePushSubject(@PathVariable Long id) {
        log.debug("REST request to delete PushSubject : {}", id);
        pushSubjectService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
