package com.lazulite.base.repository;

import com.lazulite.base.domain.ServiceWindow;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceWindow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceWindowRepository extends JpaRepository<ServiceWindow, Long> {

}
