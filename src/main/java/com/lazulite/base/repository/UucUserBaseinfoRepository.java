package com.lazulite.base.repository;

import com.lazulite.base.domain.UucUserBaseinfo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UucUserBaseinfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UucUserBaseinfoRepository extends JpaRepository<UucUserBaseinfo, Long> {
}
