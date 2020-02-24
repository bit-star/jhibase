package com.lazulite.base.service;

import com.lazulite.base.domain.ServiceWindow;
import com.lazulite.base.repository.ServiceWindowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ServiceWindow}.
 */
@Service
@Transactional
public class ServiceWindowService {

    private final Logger log = LoggerFactory.getLogger(ServiceWindowService.class);

    private final ServiceWindowRepository serviceWindowRepository;

    public ServiceWindowService(ServiceWindowRepository serviceWindowRepository) {
        this.serviceWindowRepository = serviceWindowRepository;
    }

    /**
     * Save a serviceWindow.
     *
     * @param serviceWindow the entity to save.
     * @return the persisted entity.
     */
    public ServiceWindow save(ServiceWindow serviceWindow) {
        log.debug("Request to save ServiceWindow : {}", serviceWindow);
        return serviceWindowRepository.save(serviceWindow);
    }

    /**
     * Get all the serviceWindows.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ServiceWindow> findAll() {
        log.debug("Request to get all ServiceWindows");
        return serviceWindowRepository.findAll();
    }

    /**
     * Get one serviceWindow by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ServiceWindow> findOne(Long id) {
        log.debug("Request to get ServiceWindow : {}", id);
        return serviceWindowRepository.findById(id);
    }

    /**
     * Delete the serviceWindow by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ServiceWindow : {}", id);
        serviceWindowRepository.deleteById(id);
    }
}
