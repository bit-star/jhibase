package com.lazulite.base.repository;

import com.lazulite.base.domain.MsgReceiverGroup;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MsgReceiverGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MsgReceiverGroupRepository extends JpaRepository<MsgReceiverGroup, Long> {
}
