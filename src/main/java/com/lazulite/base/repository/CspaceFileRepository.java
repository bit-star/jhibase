package com.lazulite.base.repository;
import com.lazulite.base.domain.CspaceFile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CspaceFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CspaceFileRepository extends JpaRepository<CspaceFile, Long> {

}
