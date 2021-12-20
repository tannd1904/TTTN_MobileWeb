package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.Promotion;
import com.tannd.commercemanager.model.key.PromotionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionRepository extends AbstractRepository<Promotion, PromotionId> {
  @Query(value = "select d.* " +
          "from uudai d " +
          "where d.MADONGSP = :id", nativeQuery = true)
  List<Promotion> getByProductId(@Param("id") Long id);

  @Query(value = "select d.* " +
          "from uudai d " +
          "where d.MAPK = :id", nativeQuery = true)
  List<Promotion> getByAccessoryId(@Param("id") Long id);

}
