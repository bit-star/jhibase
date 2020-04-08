package com.lazulite.base.service;

import com.lazulite.base.domain.PushSubject;
import com.lazulite.base.repository.PushSubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link PushSubject}.
 */
@Service
@Transactional
public class PushSubjectService {

    private final Logger log = LoggerFactory.getLogger(PushSubjectService.class);

    private final PushSubjectRepository pushSubjectRepository;

    public PushSubjectService(PushSubjectRepository pushSubjectRepository) {
        this.pushSubjectRepository = pushSubjectRepository;
    }

    /**
     * Save a pushSubject.
     *
     * @param pushSubject the entity to save.
     * @return the persisted entity.
     */
    public PushSubject save(PushSubject pushSubject) {
        log.debug("Request to save PushSubject : {}", pushSubject);
        return pushSubjectRepository.save(pushSubject);
    }

    /**
     * Get all the pushSubjects.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PushSubject> findAll() {
        log.debug("Request to get all PushSubjects");
        return pushSubjectRepository.findAll();
    }

    /**
     * Get one pushSubject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PushSubject> findOne(Long id) {
        log.debug("Request to get PushSubject : {}", id);
        return pushSubjectRepository.findById(id);
    }

    /**
     * Delete the pushSubject by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PushSubject : {}", id);
        pushSubjectRepository.deleteById(id);
    }
}
