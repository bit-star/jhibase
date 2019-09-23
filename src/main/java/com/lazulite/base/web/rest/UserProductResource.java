package com.lazulite.base.web.rest;

import com.lazulite.base.domain.UserProduct;
import com.lazulite.base.repository.UserProductRepository;
import com.lazulite.base.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.lazulite.base.domain.UserProduct}.
 */
@RestController
@RequestMapping("/api")
public class UserProductResource {

    private final Logger log = LoggerFactory.getLogger(UserProductResource.class);

    private static final String ENTITY_NAME = "userProduct";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserProductRepository userProductRepository;

    public UserProductResource(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }

    /**
     * {@code POST  /user-products} : Create a new userProduct.
     *
     * @param userProduct the userProduct to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userProduct, or with status {@code 400 (Bad Request)} if the userProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-products")
    public ResponseEntity<UserProduct> createUserProduct(@RequestBody UserProduct userProduct) throws URISyntaxException {
        log.debug("REST request to save UserProduct : {}", userProduct);
        if (userProduct.getId() != null) {
            throw new BadRequestAlertException("A new userProduct cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserProduct result = userProductRepository.save(userProduct);
        return ResponseEntity.created(new URI("/api/user-products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-products} : Updates an existing userProduct.
     *
     * @param userProduct the userProduct to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userProduct,
     * or with status {@code 400 (Bad Request)} if the userProduct is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userProduct couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-products")
    public ResponseEntity<UserProduct> updateUserProduct(@RequestBody UserProduct userProduct) throws URISyntaxException {
        log.debug("REST request to update UserProduct : {}", userProduct);
        if (userProduct.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserProduct result = userProductRepository.save(userProduct);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userProduct.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-products} : get all the userProducts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userProducts in body.
     */
    @GetMapping("/user-products")
    public List<UserProduct> getAllUserProducts() {
        log.debug("REST request to get all UserProducts");
        return userProductRepository.findAll();
    }

    /**
     * {@code GET  /user-products/:id} : get the "id" userProduct.
     *
     * @param id the id of the userProduct to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userProduct, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-products/{id}")
    public ResponseEntity<UserProduct> getUserProduct(@PathVariable Long id) {
        log.debug("REST request to get UserProduct : {}", id);
        Optional<UserProduct> userProduct = userProductRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userProduct);
    }

    /**
     * {@code DELETE  /user-products/:id} : delete the "id" userProduct.
     *
     * @param id the id of the userProduct to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-products/{id}")
    public ResponseEntity<Void> deleteUserProduct(@PathVariable Long id) {
        log.debug("REST request to delete UserProduct : {}", id);
        userProductRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
