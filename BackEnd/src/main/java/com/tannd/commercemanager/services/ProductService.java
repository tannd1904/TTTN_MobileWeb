package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.message.request.ProductRequest;
import com.tannd.commercemanager.model.Product;

import java.util.List;

public interface ProductService extends AbstractService<ProductDTO, Product> {
//    public ProductDTO save(ProductRequest productRequest);
//    public List<ProductDTO> getAllProduct();
    public List<ProductDTO> getTop8ProductNewArrival();
}
