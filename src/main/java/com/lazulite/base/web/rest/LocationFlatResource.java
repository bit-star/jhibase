package com.lazulite.base.web.rest;

import com.lazulite.base.domain.LocationFlat;
import com.lazulite.base.repository.LocationFlatRepository;
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
 * REST controller for managing {@link com.lazulite.base.domain.LocationFlat}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LocationFlatResource {

    private final Logger log = LoggerFactory.getLogger(LocationFlatResource.class);

    private static final String ENTITY_NAME = "locationFlat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocationFlatRepository locationFlatRepository;

    public LocationFlatResource(LocationFlatRepository locationFlatRepository) {
        this.locationFlatRepository = locationFlatRepository;
    }

    /**
     * {@code POST  /location-flats} : Create a new locationFlat.
     *
     * @param locationFlat the locationFlat to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new locationFlat, or with status {@code 400 (Bad Request)} if the locationFlat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/location-flats")
    public ResponseEntity<LocationFlat> createLocationFlat(@RequestBody LocationFlat locationFlat) throws URISyntaxException {
        log.debug("REST request to save LocationFlat : {}", locationFlat);
        if (locationFlat.getId() != null) {
            throw new BadRequestAlertException("A new locationFlat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocationFlat result = locationFlatRepository.save(locationFlat);
        return ResponseEntity.created(new URI("/api/location-flats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /location-flats} : Updates an existing locationFlat.
     *
     * @param locationFlat the locationFlat to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locationFlat,
     * or with status {@code 400 (Bad Request)} if the locationFlat is not valid,
     * or with status {@code 500 (Internal Server Error)} if the locationFlat couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/location-flats")
    public ResponseEntity<LocationFlat> updateLocationFlat(@RequestBody LocationFlat locationFlat) throws URISyntaxException {
        log.debug("REST request to update LocationFlat : {}", locationFlat);
        if (locationFlat.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocationFlat result = locationFlatRepository.save(locationFlat);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, locationFlat.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /location-flats} : get all the locationFlats.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of locationFlats in body.
     */
    @GetMapping("/location-flats")
    public List<LocationFlat> getAllLocationFlats() {
        log.debug("REST request to get all LocationFlats");
        return locationFlatRepository.findAll();
    }

    /**
     * {@code GET  /location-flats/:id} : get the "id" locationFlat.
     *
     * @param id the id of the locationFlat to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the locationFlat, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/location-flats/{id}")
    public ResponseEntity<LocationFlat> getLocationFlat(@PathVariable Long id) {
        log.debug("REST request to get LocationFlat : {}", id);
        Optional<LocationFlat> locationFlat = locationFlatRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(locationFlat);
    }

    /**
     * {@code DELETE  /location-flats/:id} : delete the "id" locationFlat.
     *
     * @param id the id of the locationFlat to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/location-flats/{id}")
    public ResponseEntity<Void> deleteLocationFlat(@PathVariable Long id) {
        log.debug("REST request to delete LocationFlat : {}", id);
        locationFlatRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
