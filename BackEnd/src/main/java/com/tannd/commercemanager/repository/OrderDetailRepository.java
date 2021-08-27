package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.OrderDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends AbstractRepository<OrderDetail, Long>{
    @Query(value = "select cp.* " +
            "from ct_phieudat cp " +
            "where cp.MAPD = :orderId ", nativeQuery = true)
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);
}
