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

    @Query(value = "select * " +
            "from phieudat p " +
            "where year(now()) = year(p.created_at) ", nativeQuery = true)
    List<Order> findOrdersInCurrentYear();

    @Query(value = "select * " +
            "from phieudat p " +
            "where month(now()) = month(p.created_at) ", nativeQuery = true)
    List<Order> findOrdersInCurrentMonth();

    @Query(value = "select ifnull(sum(p.GIA), 0)  " +
            "from phieudat p " +
            "where p.TRANGTHAI >= 2 " +
            "and month (p.NGAYDAT) = month (now()) ", nativeQuery = true)
    Double calculateRevenueThisMonth();

    @Query(value = "select ifnull(sum(p.GIA), 0)  " +
            "from phieudat p " +
            "where p.TRANGTHAI >= 2 " +
            "and year (p.NGAYDAT) = year (now()) ", nativeQuery = true)
    Double calculateRevenueThisYear();

    @Query(value = "select ifnull(sum(p.GIA), 0)  " +
            "from phieudat p " +
            "where p.TRANGTHAI >= 2 ", nativeQuery = true)
    Double calculateRevenueAllTime();
}
