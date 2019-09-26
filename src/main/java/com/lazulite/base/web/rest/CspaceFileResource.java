package com.lazulite.base.web.rest;

import com.lazulite.base.domain.CspaceFile;
import com.lazulite.base.repository.CspaceFileRepository;
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
 * REST controller for managing {@link com.lazulite.base.domain.CspaceFile}.
 */
@RestController
@RequestMapping("/api")
public class CspaceFileResource {

    private final Logger log = LoggerFactory.getLogger(CspaceFileResource.class);

    private static final String ENTITY_NAME = "cspaceFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CspaceFileRepository cspaceFileRepository;

    public CspaceFileResource(CspaceFileRepository cspaceFileRepository) {
        this.cspaceFileRepository = cspaceFileRepository;
    }

    /**
     * {@code POST  /cspace-files} : Create a new cspaceFile.
     *
     * @param cspaceFile the cspaceFile to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cspaceFile, or with status {@code 400 (Bad Request)} if the cspaceFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cspace-files")
    public ResponseEntity<CspaceFile> createCspaceFile(@RequestBody CspaceFile cspaceFile) throws URISyntaxException {
        log.debug("REST request to save CspaceFile : {}", cspaceFile);
        if (cspaceFile.getId() != null) {
            throw new BadRequestAlertException("A new cspaceFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CspaceFile result = cspaceFileRepository.save(cspaceFile);
        return ResponseEntity.created(new URI("/api/cspace-files/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cspace-files} : Updates an existing cspaceFile.
     *
     * @param cspaceFile the cspaceFile to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cspaceFile,
     * or with status {@code 400 (Bad Request)} if the cspaceFile is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cspaceFile couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cspace-files")
    public ResponseEntity<CspaceFile> updateCspaceFile(@RequestBody CspaceFile cspaceFile) throws URISyntaxException {
        log.debug("REST request to update CspaceFile : {}", cspaceFile);
        if (cspaceFile.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CspaceFile result = cspaceFileRepository.save(cspaceFile);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cspaceFile.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cspace-files} : get all the cspaceFiles.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cspaceFiles in body.
     */
    @GetMapping("/cspace-files")
    public List<CspaceFile> getAllCspaceFiles() {
        log.debug("REST request to get all CspaceFiles");
        return cspaceFileRepository.findAll();
    }

    /**
     * {@code GET  /cspace-files/:id} : get the "id" cspaceFile.
     *
     * @param id the id of the cspaceFile to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cspaceFile, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cspace-files/{id}")
    public ResponseEntity<CspaceFile> getCspaceFile(@PathVariable Long id) {
        log.debug("REST request to get CspaceFile : {}", id);
        Optional<CspaceFile> cspaceFile = cspaceFileRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cspaceFile);
    }

    /**
     * {@code DELETE  /cspace-files/:id} : delete the "id" cspaceFile.
     *
     * @param id the id of the cspaceFile to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cspace-files/{id}")
    public ResponseEntity<Void> deleteCspaceFile(@PathVariable Long id) {
        log.debug("REST request to delete CspaceFile : {}", id);
        cspaceFileRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
