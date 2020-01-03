package com.lazulite.base.repository;

import com.lazulite.base.domain.LocationDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LocationDTO entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationDTORepository extends JpaRepository<LocationDTO, Long> {

}
