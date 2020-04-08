package com.lazulite.base.repository;

import com.lazulite.base.domain.PushSubject;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PushSubject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PushSubjectRepository extends JpaRepository<PushSubject, Long> {
}
