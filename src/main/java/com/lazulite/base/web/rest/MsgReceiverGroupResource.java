package com.lazulite.base.web.rest;

import com.lazulite.base.domain.MsgReceiverGroup;
import com.lazulite.base.service.MsgReceiverGroupService;
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
 * REST controller for managing {@link com.lazulite.base.domain.MsgReceiverGroup}.
 */
@RestController
@RequestMapping("/api")
public class MsgReceiverGroupResource {

    private final Logger log = LoggerFactory.getLogger(MsgReceiverGroupResource.class);

    private static final String ENTITY_NAME = "msgReceiverGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MsgReceiverGroupService msgReceiverGroupService;

    public MsgReceiverGroupResource(MsgReceiverGroupService msgReceiverGroupService) {
        this.msgReceiverGroupService = msgReceiverGroupService;
    }

    /**
     * {@code POST  /msg-receiver-groups} : Create a new msgReceiverGroup.
     *
     * @param msgReceiverGroup the msgReceiverGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new msgReceiverGroup, or with status {@code 400 (Bad Request)} if the msgReceiverGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/msg-receiver-groups")
    public ResponseEntity<MsgReceiverGroup> createMsgReceiverGroup(@RequestBody MsgReceiverGroup msgReceiverGroup) throws URISyntaxException {
        log.debug("REST request to save MsgReceiverGroup : {}", msgReceiverGroup);
        if (msgReceiverGroup.getId() != null) {
            throw new BadRequestAlertException("A new msgReceiverGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MsgReceiverGroup result = msgReceiverGroupService.save(msgReceiverGroup);
        return ResponseEntity.created(new URI("/api/msg-receiver-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /msg-receiver-groups} : Updates an existing msgReceiverGroup.
     *
     * @param msgReceiverGroup the msgReceiverGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated msgReceiverGroup,
     * or with status {@code 400 (Bad Request)} if the msgReceiverGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the msgReceiverGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/msg-receiver-groups")
    public ResponseEntity<MsgReceiverGroup> updateMsgReceiverGroup(@RequestBody MsgReceiverGroup msgReceiverGroup) throws URISyntaxException {
        log.debug("REST request to update MsgReceiverGroup : {}", msgReceiverGroup);
        if (msgReceiverGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MsgReceiverGroup result = msgReceiverGroupService.save(msgReceiverGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, msgReceiverGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /msg-receiver-groups} : get all the msgReceiverGroups.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of msgReceiverGroups in body.
     */
    @GetMapping("/msg-receiver-groups")
    public List<MsgReceiverGroup> getAllMsgReceiverGroups(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all MsgReceiverGroups");
        return msgReceiverGroupService.findAll();
    }

    /**
     * {@code GET  /msg-receiver-groups/:id} : get the "id" msgReceiverGroup.
     *
     * @param id the id of the msgReceiverGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the msgReceiverGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/msg-receiver-groups/{id}")
    public ResponseEntity<MsgReceiverGroup> getMsgReceiverGroup(@PathVariable Long id) {
        log.debug("REST request to get MsgReceiverGroup : {}", id);
        Optional<MsgReceiverGroup> msgReceiverGroup = msgReceiverGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(msgReceiverGroup);
    }

    /**
     * {@code DELETE  /msg-receiver-groups/:id} : delete the "id" msgReceiverGroup.
     *
     * @param id the id of the msgReceiverGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/msg-receiver-groups/{id}")
    public ResponseEntity<Void> deleteMsgReceiverGroup(@PathVariable Long id) {
        log.debug("REST request to delete MsgReceiverGroup : {}", id);
        msgReceiverGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
