package com.lazulite.base.web.rest;

import com.lazulite.base.domain.LocationVM;
import com.lazulite.base.repository.LocationVMRepository;
import com.lazulite.base.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lazulite.base.domain.LocationVM}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LocationVMResource {

    private final Logger log = LoggerFactory.getLogger(LocationVMResource.class);

    private static final String ENTITY_NAME = "locationVM";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocationVMRepository locationVMRepository;

    public LocationVMResource(LocationVMRepository locationVMRepository) {
        this.locationVMRepository = locationVMRepository;
    }

    /**
     * {@code POST  /location-vms} : Create a new locationVM.
     *
     * @param locationVM the locationVM to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new locationVM, or with status {@code 400 (Bad Request)} if the locationVM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/location-vms")
    public ResponseEntity<LocationVM> createLocationVM(@RequestBody LocationVM locationVM) throws URISyntaxException {
        log.debug("REST request to save LocationVM : {}", locationVM);
        if (locationVM.getId() != null) {
            throw new BadRequestAlertException("A new locationVM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocationVM result = locationVMRepository.save(locationVM);
        return ResponseEntity.created(new URI("/api/location-vms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /location-vms} : Updates an existing locationVM.
     *
     * @param locationVM the locationVM to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locationVM,
     * or with status {@code 400 (Bad Request)} if the locationVM is not valid,
     * or with status {@code 500 (Internal Server Error)} if the locationVM couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/location-vms")
    public ResponseEntity<LocationVM> updateLocationVM(@RequestBody LocationVM locationVM) throws URISyntaxException {
        log.debug("REST request to update LocationVM : {}", locationVM);
        if (locationVM.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocationVM result = locationVMRepository.save(locationVM);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, locationVM.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /location-vms} : get all the locationVMS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of locationVMS in body.
     */
    @GetMapping("/location-vms")
    public List<LocationVM> getAllLocationVMS() {
        log.debug("REST request to get all LocationVMS");
        return locationVMRepository.findAll();
    }

    /**
     * {@code GET  /location-vms/:id} : get the "id" locationVM.
     *
     * @param id the id of the locationVM to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the locationVM, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/location-vms/{id}")
    public ResponseEntity<LocationVM> getLocationVM(@PathVariable Long id) {
        log.debug("REST request to get LocationVM : {}", id);
        Optional<LocationVM> locationVM = locationVMRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(locationVM);
    }

    /**
     * {@code DELETE  /location-vms/:id} : delete the "id" locationVM.
     *
     * @param id the id of the locationVM to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/location-vms/{id}")
    public ResponseEntity<Void> deleteLocationVM(@PathVariable Long id) {
        log.debug("REST request to delete LocationVM : {}", id);
        locationVMRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
