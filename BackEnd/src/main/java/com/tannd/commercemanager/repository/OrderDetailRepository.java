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

    @Query(value = "select count(t.MASP) " +
            "from (select cp.* " +
            "from ct_phieudat cp " +
            "left join sanpham s " +
            "on cp.MASP = s.MASP " +
            "and s.TRANGTHAI = 1) t " +
            "where month(t.created_at) = month(now()) " +
            "and year(t.created_at) = year(now()) ", nativeQuery = true)
    Long countExportProductThisMonth();

    @Query(value = "select count(t.MASP) " +
            "from (select cp.* " +
            "from ct_phieudat cp " +
            "left join sanpham s " +
            "on cp.MASP = s.MASP " +
            "and s.TRANGTHAI = 1) t " +
            "where month(t.created_at) = :month " +
            "and year(t.created_at) = year(now()) ", nativeQuery = true)
    Long countExportProductInMonth(@Param("month") Integer month);
}
