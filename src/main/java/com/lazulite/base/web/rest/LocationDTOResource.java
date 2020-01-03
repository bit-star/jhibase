package com.lazulite.base.web.rest;

import com.lazulite.base.domain.LocationDTO;
import com.lazulite.base.repository.LocationDTORepository;
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
 * REST controller for managing {@link com.lazulite.base.domain.LocationDTO}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LocationDTOResource {

    private final Logger log = LoggerFactory.getLogger(LocationDTOResource.class);

    private static final String ENTITY_NAME = "locationDTO";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocationDTORepository locationDTORepository;

    public LocationDTOResource(LocationDTORepository locationDTORepository) {
        this.locationDTORepository = locationDTORepository;
    }

    /**
     * {@code POST  /location-dtos} : Create a new locationDTO.
     *
     * @param locationDTO the locationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new locationDTO, or with status {@code 400 (Bad Request)} if the locationDTO has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/location-dtos")
    public ResponseEntity<LocationDTO> createLocationDTO(@RequestBody LocationDTO locationDTO) throws URISyntaxException {
        log.debug("REST request to save LocationDTO : {}", locationDTO);
        if (locationDTO.getId() != null) {
            throw new BadRequestAlertException("A new locationDTO cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocationDTO result = locationDTORepository.save(locationDTO);
        return ResponseEntity.created(new URI("/api/location-dtos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /location-dtos} : Updates an existing locationDTO.
     *
     * @param locationDTO the locationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locationDTO,
     * or with status {@code 400 (Bad Request)} if the locationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the locationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/location-dtos")
    public ResponseEntity<LocationDTO> updateLocationDTO(@RequestBody LocationDTO locationDTO) throws URISyntaxException {
        log.debug("REST request to update LocationDTO : {}", locationDTO);
        if (locationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocationDTO result = locationDTORepository.save(locationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, locationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /location-dtos} : get all the locationDTOS.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of locationDTOS in body.
     */
    @GetMapping("/location-dtos")
    public List<LocationDTO> getAllLocationDTOS() {
        log.debug("REST request to get all LocationDTOS");
        return locationDTORepository.findAll();
    }

    /**
     * {@code GET  /location-dtos/:id} : get the "id" locationDTO.
     *
     * @param id the id of the locationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the locationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/location-dtos/{id}")
    public ResponseEntity<LocationDTO> getLocationDTO(@PathVariable Long id) {
        log.debug("REST request to get LocationDTO : {}", id);
        Optional<LocationDTO> locationDTO = locationDTORepository.findById(id);
        return ResponseUtil.wrapOrNotFound(locationDTO);
    }

    /**
     * {@code DELETE  /location-dtos/:id} : delete the "id" locationDTO.
     *
     * @param id the id of the locationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/location-dtos/{id}")
    public ResponseEntity<Void> deleteLocationDTO(@PathVariable Long id) {
        log.debug("REST request to delete LocationDTO : {}", id);
        locationDTORepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
