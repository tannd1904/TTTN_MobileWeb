package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ProductDetailRepository extends AbstractRepository<ProductDetail, Long> {
}
