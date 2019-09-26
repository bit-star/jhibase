package com.lazulite.base.repository;
import com.lazulite.base.domain.GovernmentReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GovernmentReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GovernmentReportRepository extends JpaRepository<GovernmentReport, Long> {

}
