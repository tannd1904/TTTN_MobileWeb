package com.tannd.commercemanager.repository;

import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends AbstractRepository<Product, Long> {
//    @Query(value = "SELECT DISTINCT d.*" +
////            "d.MADONGSP, d.TENDONGSP ,d.GIA ,d.HINHANH ," +
////            "d.TRANGTHAI ,d.MOTA , d.LOAI, d.MAHANG , d.created_at , d.updated_at " +
//            "FROM dongsp d " +
//            "INNER JOIN ct_phieunhap cp " +
//            "ON d.MADONGSP = cp.MADONGSP AND cp.SOLUONG > 0 " +
////            "ORDER BY d.created_at DESC"
//            "LIMIT 4"
//             , nativeQuery = true)
//    List<Product> findTop4NewArrivalProducts();

    @Query(value = "select distinct d.* " +
            "from ct_phieunhap cp " +
            "left join dongsp d " +
            "on cp.MADONGSP = d.MADONGSP ", nativeQuery = true)
    List<Product> findAllProductImported();

    @Query(value = "select t.*" +
            "from (select distinct d.* " +
            "from dongsp d " +
            "inner join ct_phieunhap cp " +
            "on d.MADONGSP = cp.MADONGSP and cp.SOLUONG > 0 " +
            "order by d.created_at desc ) t " +
            "limit 8",
            nativeQuery = true)
    List<Product> findTop8NewArrivalProducts();

    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByOrderByPriceAsc();

    @Query(value = "select d.* " +
            "from dongsp d " +
            "where d.MAHANG = :categoryId", nativeQuery = true)
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select distinct d.* " +
            "from ct_phieunhap cp " +
            "inner join dongsp d " +
            "on cp.MADONGSP = d.MADONGSP " +
            "and d.MAHANG = :categoryId ", nativeQuery = true)
    List<Product> findImportedByCategoryId(@Param("categoryId") Long categoryId);

    List<Product> findAllByNameContaining(String name);
}
