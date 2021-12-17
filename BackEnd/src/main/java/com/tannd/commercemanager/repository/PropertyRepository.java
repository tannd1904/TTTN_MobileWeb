package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends AbstractRepository<Property, Long> {
  @Query(value = "select d.* " +
          "from thuoctinh d " +
          "where d.MADONGSP = :productId", nativeQuery = true)
  List<Property> findByProductId(@Param("productId") Long productId);
}
