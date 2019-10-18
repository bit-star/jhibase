package com.lazulite.base.repository;
import com.lazulite.base.domain.HistorySearch;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HistorySearch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistorySearchRepository extends JpaRepository<HistorySearch, Long> {

}
