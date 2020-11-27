package com.lazulite.base.service;

import com.lazulite.base.domain.MicroAppGroup;
import com.lazulite.base.repository.MicroAppGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MicroAppGroup}.
 */
@Service
@Transactional
public class MicroAppGroupService {

    private final Logger log = LoggerFactory.getLogger(MicroAppGroupService.class);

    private final MicroAppGroupRepository microAppGroupRepository;

    public MicroAppGroupService(MicroAppGroupRepository microAppGroupRepository) {
        this.microAppGroupRepository = microAppGroupRepository;
    }

    /**
     * Save a microAppGroup.
     *
     * @param microAppGroup the entity to save.
     * @return the persisted entity.
     */
    public MicroAppGroup save(MicroAppGroup microAppGroup) {
        log.debug("Request to save MicroAppGroup : {}", microAppGroup);
        return microAppGroupRepository.save(microAppGroup);
    }

    /**
     * Get all the microAppGroups.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MicroAppGroup> findAll() {
        log.debug("Request to get all MicroAppGroups");
        return microAppGroupRepository.findAllWithEagerRelationships();
    }


    /**
     * Get all the microAppGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MicroAppGroup> findAllWithEagerRelationships(Pageable pageable) {
        return microAppGroupRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one microAppGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MicroAppGroup> findOne(Long id) {
        log.debug("Request to get MicroAppGroup : {}", id);
        return microAppGroupRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the microAppGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MicroAppGroup : {}", id);
        microAppGroupRepository.deleteById(id);
    }
}
