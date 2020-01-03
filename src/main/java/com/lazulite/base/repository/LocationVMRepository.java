package com.lazulite.base.repository;

import com.lazulite.base.domain.LocationVM;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LocationVM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationVMRepository extends JpaRepository<LocationVM, Long> {

}
