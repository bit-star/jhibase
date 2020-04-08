package com.lazulite.base.web.rest;

import com.lazulite.base.domain.UucDepartmentTree;
import com.lazulite.base.service.UucDepartmentTreeService;
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
 * REST controller for managing {@link com.lazulite.base.domain.UucDepartmentTree}.
 */
@RestController
@RequestMapping("/api")
public class UucDepartmentTreeResource {

    private final Logger log = LoggerFactory.getLogger(UucDepartmentTreeResource.class);

    private static final String ENTITY_NAME = "uucDepartmentTree";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UucDepartmentTreeService uucDepartmentTreeService;

    public UucDepartmentTreeResource(UucDepartmentTreeService uucDepartmentTreeService) {
        this.uucDepartmentTreeService = uucDepartmentTreeService;
    }

    /**
     * {@code POST  /uuc-department-trees} : Create a new uucDepartmentTree.
     *
     * @param uucDepartmentTree the uucDepartmentTree to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uucDepartmentTree, or with status {@code 400 (Bad Request)} if the uucDepartmentTree has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/uuc-department-trees")
    public ResponseEntity<UucDepartmentTree> createUucDepartmentTree(@RequestBody UucDepartmentTree uucDepartmentTree) throws URISyntaxException {
        log.debug("REST request to save UucDepartmentTree : {}", uucDepartmentTree);
        if (uucDepartmentTree.getId() != null) {
            throw new BadRequestAlertException("A new uucDepartmentTree cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UucDepartmentTree result = uucDepartmentTreeService.save(uucDepartmentTree);
        return ResponseEntity.created(new URI("/api/uuc-department-trees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /uuc-department-trees} : Updates an existing uucDepartmentTree.
     *
     * @param uucDepartmentTree the uucDepartmentTree to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uucDepartmentTree,
     * or with status {@code 400 (Bad Request)} if the uucDepartmentTree is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uucDepartmentTree couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/uuc-department-trees")
    public ResponseEntity<UucDepartmentTree> updateUucDepartmentTree(@RequestBody UucDepartmentTree uucDepartmentTree) throws URISyntaxException {
        log.debug("REST request to update UucDepartmentTree : {}", uucDepartmentTree);
        if (uucDepartmentTree.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UucDepartmentTree result = uucDepartmentTreeService.save(uucDepartmentTree);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uucDepartmentTree.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /uuc-department-trees} : get all the uucDepartmentTrees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uucDepartmentTrees in body.
     */
    @GetMapping("/uuc-department-trees")
    public List<UucDepartmentTree> getAllUucDepartmentTrees() {
        log.debug("REST request to get all UucDepartmentTrees");
        return uucDepartmentTreeService.findAll();
    }

    /**
     * {@code GET  /uuc-department-trees/:id} : get the "id" uucDepartmentTree.
     *
     * @param id the id of the uucDepartmentTree to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uucDepartmentTree, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/uuc-department-trees/{id}")
    public ResponseEntity<UucDepartmentTree> getUucDepartmentTree(@PathVariable Long id) {
        log.debug("REST request to get UucDepartmentTree : {}", id);
        Optional<UucDepartmentTree> uucDepartmentTree = uucDepartmentTreeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uucDepartmentTree);
    }

    /**
     * {@code DELETE  /uuc-department-trees/:id} : delete the "id" uucDepartmentTree.
     *
     * @param id the id of the uucDepartmentTree to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/uuc-department-trees/{id}")
    public ResponseEntity<Void> deleteUucDepartmentTree(@PathVariable Long id) {
        log.debug("REST request to delete UucDepartmentTree : {}", id);
        uucDepartmentTreeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
