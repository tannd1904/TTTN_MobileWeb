package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.model.ProductDetail;

import java.util.List;

public interface ProductDetailService extends AbstractService<ProductDetailDTO, ProductDetail> {
//    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException;
    List<ProductDetailDTO> getProductDetailByProductId(Long id);
    List<ProductDetailDTO> getAllProductDetailByProductId(Long id);
    List<ProductDetailDTO> getProductDetailByProductIdAndDetail(Long id,
                                            String ram, String color, String memmory);
    List<ProductDetail> getProductDetailEntityByProductIdAndDetail(Long id,
                                            String ram, String color, String memmory);
    Long countProductDetailByProductId(Long id);
    Long countProductDetailByProductIdAndDetail(Long id,
                                            String ram, String color, String memmory);

    Long countImportProductThisMonth();
    Long countImportProductInMonth(Integer month);
}
