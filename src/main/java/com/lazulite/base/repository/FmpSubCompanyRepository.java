package com.lazulite.base.repository;

import com.lazulite.base.domain.FmpSubCompany;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FmpSubCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FmpSubCompanyRepository extends JpaRepository<FmpSubCompany, Long> {

}
