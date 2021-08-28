package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Order, Long>{
    @Query(value = "select * " +
            "from phieudat p " +
            "where p.MAKH = :userId ", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") Long userId);
}
