package com.lazulite.base.service;

import com.lazulite.base.domain.MpHotspot;
import com.lazulite.base.repository.MpHotspotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MpHotspot}.
 */
@Service
@Transactional
public class MpHotspotService {

    private final Logger log = LoggerFactory.getLogger(MpHotspotService.class);

    private final MpHotspotRepository mpHotspotRepository;

    public MpHotspotService(MpHotspotRepository mpHotspotRepository) {
        this.mpHotspotRepository = mpHotspotRepository;
    }

    /**
     * Save a mpHotspot.
     *
     * @param mpHotspot the entity to save.
     * @return the persisted entity.
     */
    public MpHotspot save(MpHotspot mpHotspot) {
        log.debug("Request to save MpHotspot : {}", mpHotspot);
        return mpHotspotRepository.save(mpHotspot);
    }

    /**
     * Get all the mpHotspots.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MpHotspot> findAll() {
        log.debug("Request to get all MpHotspots");
        return mpHotspotRepository.findAll();
    }


    /**
     * Get one mpHotspot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MpHotspot> findOne(Long id) {
        log.debug("Request to get MpHotspot : {}", id);
        return mpHotspotRepository.findById(id);
    }

    /**
     * Delete the mpHotspot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MpHotspot : {}", id);
        mpHotspotRepository.deleteById(id);
    }
}
