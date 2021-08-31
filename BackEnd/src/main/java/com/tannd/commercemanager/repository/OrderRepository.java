package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends AbstractRepository<Order, Long>{
    @Query(value = "select * " +
            "from phieudat p " +
            "where p.MAKH = :userId ", nativeQuery = true)
    List<Order> findAllByUserId(@Param("userId") Long userId);

    @Query(value = "select * " +
            "from phieudat p " +
            "where month(now()) - month(p.created_at) < :n_month " , nativeQuery = true)
    List<User> findOrderInMonthCurrent(@Param("n_month") Integer n_month);
}
