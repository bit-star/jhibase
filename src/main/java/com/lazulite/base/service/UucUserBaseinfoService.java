package com.lazulite.base.service;

import com.lazulite.base.domain.UucUserBaseinfo;
import com.lazulite.base.repository.UucUserBaseinfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link UucUserBaseinfo}.
 */
@Service
@Transactional
public class UucUserBaseinfoService {

    private final Logger log = LoggerFactory.getLogger(UucUserBaseinfoService.class);

    private final UucUserBaseinfoRepository uucUserBaseinfoRepository;

    public UucUserBaseinfoService(UucUserBaseinfoRepository uucUserBaseinfoRepository) {
        this.uucUserBaseinfoRepository = uucUserBaseinfoRepository;
    }

    /**
     * Save a uucUserBaseinfo.
     *
     * @param uucUserBaseinfo the entity to save.
     * @return the persisted entity.
     */
    public UucUserBaseinfo save(UucUserBaseinfo uucUserBaseinfo) {
        log.debug("Request to save UucUserBaseinfo : {}", uucUserBaseinfo);
        return uucUserBaseinfoRepository.save(uucUserBaseinfo);
    }

    /**
     * Get all the uucUserBaseinfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UucUserBaseinfo> findAll() {
        log.debug("Request to get all UucUserBaseinfos");
        return uucUserBaseinfoRepository.findAll();
    }

    /**
     * Get one uucUserBaseinfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UucUserBaseinfo> findOne(Long id) {
        log.debug("Request to get UucUserBaseinfo : {}", id);
        return uucUserBaseinfoRepository.findById(id);
    }

    /**
     * Delete the uucUserBaseinfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UucUserBaseinfo : {}", id);
        uucUserBaseinfoRepository.deleteById(id);
    }
}
