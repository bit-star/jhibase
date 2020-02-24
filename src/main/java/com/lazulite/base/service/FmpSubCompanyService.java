package com.lazulite.base.service;

import com.lazulite.base.domain.FmpSubCompany;
import com.lazulite.base.repository.FmpSubCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link FmpSubCompany}.
 */
@Service
@Transactional
public class FmpSubCompanyService {

    private final Logger log = LoggerFactory.getLogger(FmpSubCompanyService.class);

    private final FmpSubCompanyRepository fmpSubCompanyRepository;

    public FmpSubCompanyService(FmpSubCompanyRepository fmpSubCompanyRepository) {
        this.fmpSubCompanyRepository = fmpSubCompanyRepository;
    }

    /**
     * Save a fmpSubCompany.
     *
     * @param fmpSubCompany the entity to save.
     * @return the persisted entity.
     */
    public FmpSubCompany save(FmpSubCompany fmpSubCompany) {
        log.debug("Request to save FmpSubCompany : {}", fmpSubCompany);
        return fmpSubCompanyRepository.save(fmpSubCompany);
    }

    /**
     * Get all the fmpSubCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FmpSubCompany> findAll() {
        log.debug("Request to get all FmpSubCompanies");
        return fmpSubCompanyRepository.findAll();
    }

    /**
     * Get one fmpSubCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FmpSubCompany> findOne(Long id) {
        log.debug("Request to get FmpSubCompany : {}", id);
        return fmpSubCompanyRepository.findById(id);
    }

    /**
     * Delete the fmpSubCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FmpSubCompany : {}", id);
        fmpSubCompanyRepository.deleteById(id);
    }
}
