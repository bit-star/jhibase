package com.lazulite.base.repository;
import com.lazulite.base.domain.UserProduct;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the UserProduct entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Long> {

    @Query("select userProduct from UserProduct userProduct where userProduct.user.login = ?#{principal.username}")
    List<UserProduct> findByUserIsCurrentUser();

}
