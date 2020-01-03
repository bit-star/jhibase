package com.lazulite.base.repository;

import com.lazulite.base.domain.LocationFlat;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LocationFlat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationFlatRepository extends JpaRepository<LocationFlat, Long> {

}
