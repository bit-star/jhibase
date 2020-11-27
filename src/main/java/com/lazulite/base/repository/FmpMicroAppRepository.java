package com.lazulite.base.repository;

import com.lazulite.base.domain.FmpMicroApp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FmpMicroApp entity.
 */
@Repository
public interface FmpMicroAppRepository extends JpaRepository<FmpMicroApp, Long> {

    @Query(value = "select distinct fmpMicroApp from FmpMicroApp fmpMicroApp left join fetch fmpMicroApp.uucDepartmentTrees left join fetch fmpMicroApp.usableUsers",
        countQuery = "select count(distinct fmpMicroApp) from FmpMicroApp fmpMicroApp")
    Page<FmpMicroApp> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct fmpMicroApp from FmpMicroApp fmpMicroApp left join fetch fmpMicroApp.uucDepartmentTrees left join fetch fmpMicroApp.usableUsers")
    List<FmpMicroApp> findAllWithEagerRelationships();

    @Query("select fmpMicroApp from FmpMicroApp fmpMicroApp left join fetch fmpMicroApp.uucDepartmentTrees left join fetch fmpMicroApp.usableUsers where fmpMicroApp.id =:id")
    Optional<FmpMicroApp> findOneWithEagerRelationships(@Param("id") Long id);
}
