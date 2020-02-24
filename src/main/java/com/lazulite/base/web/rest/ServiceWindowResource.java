package com.lazulite.base.web.rest;

import com.lazulite.base.domain.ServiceWindow;
import com.lazulite.base.service.ServiceWindowService;
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
 * REST controller for managing {@link com.lazulite.base.domain.ServiceWindow}.
 */
@RestController
@RequestMapping("/api")
public class ServiceWindowResource {

    private final Logger log = LoggerFactory.getLogger(ServiceWindowResource.class);

    private static final String ENTITY_NAME = "serviceWindow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceWindowService serviceWindowService;

    public ServiceWindowResource(ServiceWindowService serviceWindowService) {
        this.serviceWindowService = serviceWindowService;
    }

    /**
     * {@code POST  /service-windows} : Create a new serviceWindow.
     *
     * @param serviceWindow the serviceWindow to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceWindow, or with status {@code 400 (Bad Request)} if the serviceWindow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-windows")
    public ResponseEntity<ServiceWindow> createServiceWindow(@RequestBody ServiceWindow serviceWindow) throws URISyntaxException {
        log.debug("REST request to save ServiceWindow : {}", serviceWindow);
        if (serviceWindow.getId() != null) {
            throw new BadRequestAlertException("A new serviceWindow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceWindow result = serviceWindowService.save(serviceWindow);
        return ResponseEntity.created(new URI("/api/service-windows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-windows} : Updates an existing serviceWindow.
     *
     * @param serviceWindow the serviceWindow to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceWindow,
     * or with status {@code 400 (Bad Request)} if the serviceWindow is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceWindow couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-windows")
    public ResponseEntity<ServiceWindow> updateServiceWindow(@RequestBody ServiceWindow serviceWindow) throws URISyntaxException {
        log.debug("REST request to update ServiceWindow : {}", serviceWindow);
        if (serviceWindow.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceWindow result = serviceWindowService.save(serviceWindow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceWindow.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-windows} : get all the serviceWindows.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceWindows in body.
     */
    @GetMapping("/service-windows")
    public List<ServiceWindow> getAllServiceWindows() {
        log.debug("REST request to get all ServiceWindows");
        return serviceWindowService.findAll();
    }

    /**
     * {@code GET  /service-windows/:id} : get the "id" serviceWindow.
     *
     * @param id the id of the serviceWindow to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceWindow, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-windows/{id}")
    public ResponseEntity<ServiceWindow> getServiceWindow(@PathVariable Long id) {
        log.debug("REST request to get ServiceWindow : {}", id);
        Optional<ServiceWindow> serviceWindow = serviceWindowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceWindow);
    }

    /**
     * {@code DELETE  /service-windows/:id} : delete the "id" serviceWindow.
     *
     * @param id the id of the serviceWindow to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-windows/{id}")
    public ResponseEntity<Void> deleteServiceWindow(@PathVariable Long id) {
        log.debug("REST request to delete ServiceWindow : {}", id);
        serviceWindowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
