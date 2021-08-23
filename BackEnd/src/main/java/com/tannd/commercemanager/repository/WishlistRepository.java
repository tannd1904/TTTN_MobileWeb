package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Wishlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends AbstractRepository<Wishlist, Long>{
    @Query(value = "select y.* " +
            "from yeuthich y " +
            "where y.MAKH = :userId " , nativeQuery = true)
    List<Wishlist> findByUserId(@Param("userId") Long userId);

    @Query(value = "select y.* " +
            "from yeuthich y " +
            "where y.MADONGSP = :productId ", nativeQuery = true)
    Wishlist findByProductId(@Param("productId") Long productId);
}
