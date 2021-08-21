package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends AbstractRepository<ProductDetail, Long> {
    @Query(value = "select s.* " +
            "from sanpham s " +
            "inner join ct_phieunhap cp " +
            "on cp.MACTPN = s.MACTPN " +
            "inner join dongsp d " +
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId" , nativeQuery = true)
    List<ProductDetail> findProductDetailByProductId(@Param("productId") Long productId);
}
