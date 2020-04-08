package com.lazulite.base.service;

import com.lazulite.base.domain.MsgReceiverGroup;
import com.lazulite.base.repository.MsgReceiverGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MsgReceiverGroup}.
 */
@Service
@Transactional
public class MsgReceiverGroupService {

    private final Logger log = LoggerFactory.getLogger(MsgReceiverGroupService.class);

    private final MsgReceiverGroupRepository msgReceiverGroupRepository;

    public MsgReceiverGroupService(MsgReceiverGroupRepository msgReceiverGroupRepository) {
        this.msgReceiverGroupRepository = msgReceiverGroupRepository;
    }

    /**
     * Save a msgReceiverGroup.
     *
     * @param msgReceiverGroup the entity to save.
     * @return the persisted entity.
     */
    public MsgReceiverGroup save(MsgReceiverGroup msgReceiverGroup) {
        log.debug("Request to save MsgReceiverGroup : {}", msgReceiverGroup);
        return msgReceiverGroupRepository.save(msgReceiverGroup);
    }

    /**
     * Get all the msgReceiverGroups.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MsgReceiverGroup> findAll() {
        log.debug("Request to get all MsgReceiverGroups");
        return msgReceiverGroupRepository.findAll();
    }

    /**
     * Get one msgReceiverGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MsgReceiverGroup> findOne(Long id) {
        log.debug("Request to get MsgReceiverGroup : {}", id);
        return msgReceiverGroupRepository.findById(id);
    }

    /**
     * Delete the msgReceiverGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MsgReceiverGroup : {}", id);
        msgReceiverGroupRepository.deleteById(id);
    }
}
