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
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId " +
            "where s.TRANGTHAI = 0 " , nativeQuery = true)
    List<ProductDetail> findProductDetailByProductId(@Param("productId") Long productId);

    @Query(value = "select s.* " +
            "from sanpham s " +
            "inner join ct_phieunhap cp " +
            "on cp.MACTPN = s.MACTPN " +
            "inner join dongsp d " +
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId ", nativeQuery = true)
    List<ProductDetail> findAllProductDetailByProductId(@Param("productId") Long productId);

    @Query(value = "select s.* " +
            "from sanpham s " +
            "inner join ct_phieunhap cp " +
            "on cp.MACTPN = s.MACTPN " +
            "inner join dongsp d " +
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId " +
            "where s.TRANGTHAI = 0 " +
            "and (s.RAM = :ram " +
            "and s.COLOR = :color " +
            "and s.MEMMORY = :memmory )" , nativeQuery = true)
    List<ProductDetail> findProductDetailByProductIdAndDetail(
            @Param("productId") Long productId,
            @Param("ram") String ram,
            @Param("color") String color,
            @Param("memmory") String memmory);

    @Query(value = "select count(s.MASP) " +
            "from sanpham s " +
            "inner join ct_phieunhap cp " +
            "on cp.MACTPN = s.MACTPN " +
            "inner join dongsp d " +
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId " +
            "where s.TRANGTHAI = 0", nativeQuery = true)
    Long countProductDetailByProductId(@Param("productId") Long productId);

    @Query(value = "select count(s.MASP) " +
            "from sanpham s " +
            "inner join ct_phieunhap cp " +
            "on cp.MACTPN = s.MACTPN " +
            "inner join dongsp d " +
            "on d.MADONGSP = cp.MADONGSP and d.MADONGSP = :productId " +
            "where s.TRANGTHAI = 0 " +
            "and (s.RAM = :ram " +
            "and s.COLOR = :color " +
            "and s.MEMMORY = :memmory )" , nativeQuery = true)
    Long countProductDetailByProductIdAndDetail(
            @Param("productId") Long productId,
            @Param("ram") String ram,
            @Param("color") String color,
            @Param("memmory") String memmory);

    @Query(value = "select count(s.MASP) " +
            "from sanpham s " +
            "where month (s.created_at) = month (now()) " +
            "and year (s.created_at) = year (now()) ", nativeQuery = true)
    Long countImportProductThisMonth();

    @Query(value = "select count(s.MASP) " +
            "from sanpham s " +
            "where month (s.created_at) = :month " +
            "and year (s.created_at) = year (now()) ", nativeQuery = true)
    Long countImportProductInMonth(@Param("month") Integer month);
}
