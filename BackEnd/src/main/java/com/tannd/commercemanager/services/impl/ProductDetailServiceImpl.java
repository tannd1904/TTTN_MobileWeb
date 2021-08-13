package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.ProductDetailMapper;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.services.ProductDetailService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ServiceHelper
public class ProductDetailServiceImpl extends AbstractServiceImpl<ProductDetailRepository, ProductDetailMapper, ProductDetailDTO, ProductDetail>
        implements ProductDetailService {
    @Autowired
    ProductDetailRepository thisRepository;

    private ProductDetailMapper thisMapper;

    @Override
    public void initRepository() {
        repository = thisRepository;
    }

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProductDetailRepository getRepository() {
        initRepository();
        return repository;
    }

    @Override
    public ProductDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

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
