package com.lazulite.base.repository;

import com.lazulite.base.domain.MicroAppGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MicroAppGroup entity.
 */
@Repository
public interface MicroAppGroupRepository extends JpaRepository<MicroAppGroup, Long> {

    @Query(value = "select distinct microAppGroup from MicroAppGroup microAppGroup left join fetch microAppGroup.fmpMicroApps",
        countQuery = "select count(distinct microAppGroup) from MicroAppGroup microAppGroup")
    Page<MicroAppGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct microAppGroup from MicroAppGroup microAppGroup left join fetch microAppGroup.fmpMicroApps")
    List<MicroAppGroup> findAllWithEagerRelationships();

    @Query("select microAppGroup from MicroAppGroup microAppGroup left join fetch microAppGroup.fmpMicroApps where microAppGroup.id =:id")
    Optional<MicroAppGroup> findOneWithEagerRelationships(@Param("id") Long id);
}
