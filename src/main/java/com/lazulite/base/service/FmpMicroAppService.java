package com.lazulite.base.service;

import com.lazulite.base.domain.FmpMicroApp;
import com.lazulite.base.repository.FmpMicroAppRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FmpMicroApp}.
 */
@Service
@Transactional
public class FmpMicroAppService {

    private final Logger log = LoggerFactory.getLogger(FmpMicroAppService.class);

    private final FmpMicroAppRepository fmpMicroAppRepository;

    public FmpMicroAppService(FmpMicroAppRepository fmpMicroAppRepository) {
        this.fmpMicroAppRepository = fmpMicroAppRepository;
    }

    /**
     * Save a fmpMicroApp.
     *
     * @param fmpMicroApp the entity to save.
     * @return the persisted entity.
     */
    public FmpMicroApp save(FmpMicroApp fmpMicroApp) {
        log.debug("Request to save FmpMicroApp : {}", fmpMicroApp);
        return fmpMicroAppRepository.save(fmpMicroApp);
    }

    /**
     * Get all the fmpMicroApps.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FmpMicroApp> findAll() {
        log.debug("Request to get all FmpMicroApps");
        return fmpMicroAppRepository.findAllWithEagerRelationships();
    }


    /**
     * Get all the fmpMicroApps with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FmpMicroApp> findAllWithEagerRelationships(Pageable pageable) {
        return fmpMicroAppRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one fmpMicroApp by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FmpMicroApp> findOne(Long id) {
        log.debug("Request to get FmpMicroApp : {}", id);
        return fmpMicroAppRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the fmpMicroApp by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FmpMicroApp : {}", id);
        fmpMicroAppRepository.deleteById(id);
    }
}
