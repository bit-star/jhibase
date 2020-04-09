package com.lazulite.base.repository;

import com.lazulite.base.domain.MsgReceiverGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MsgReceiverGroup entity.
 */
@Repository
public interface MsgReceiverGroupRepository extends JpaRepository<MsgReceiverGroup, Long> {

    @Query(value = "select distinct msgReceiverGroup from MsgReceiverGroup msgReceiverGroup left join fetch msgReceiverGroup.uucDepartmentTrees left join fetch msgReceiverGroup.uucUserBaseinfos",
        countQuery = "select count(distinct msgReceiverGroup) from MsgReceiverGroup msgReceiverGroup")
    Page<MsgReceiverGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct msgReceiverGroup from MsgReceiverGroup msgReceiverGroup left join fetch msgReceiverGroup.uucDepartmentTrees left join fetch msgReceiverGroup.uucUserBaseinfos")
    List<MsgReceiverGroup> findAllWithEagerRelationships();

    @Query("select msgReceiverGroup from MsgReceiverGroup msgReceiverGroup left join fetch msgReceiverGroup.uucDepartmentTrees left join fetch msgReceiverGroup.uucUserBaseinfos where msgReceiverGroup.id =:id")
    Optional<MsgReceiverGroup> findOneWithEagerRelationships(@Param("id") Long id);
}
