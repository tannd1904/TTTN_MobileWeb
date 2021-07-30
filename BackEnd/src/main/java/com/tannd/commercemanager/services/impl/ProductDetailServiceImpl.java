package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.ProductDetailMapper;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl extends AbstractServiceImpl<ProductDetailRepository, ProductDetailMapper, ProductDetailDTO, ProductDetail>
        implements ProductDetailService {

//    @Autowired
//    ProductDetailRepository productDetailRepository;
//
//    @Autowired
//    ProductDetailMapper productDetailMapper;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Override
//    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException {
//        Product product = productRepository.findProductsByProductId(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
//        List<ProductDetail> productDetailList = productDetailRepository.findAllByProduct(product);
//        List<ProductDetailDTO> productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
//        return productDetailDTOList;
//    }
}
