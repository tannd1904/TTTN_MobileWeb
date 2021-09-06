package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.ProductDetailMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.services.ProductDetailService;
import com.tannd.commercemanager.services.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ServiceHelper
public class ProductDetailServiceImpl extends
        AbstractServiceImpl<ProductDetailRepository, ProductDetailMapper,
                ProductDetailDTO, ProductDetail>
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

    @Override
    public List<ProductDetailDTO> getProductDetailByProductId(Long id) {
        List<ProductDetail> listEntity = getRepository().findProductDetailByProductId(id);
        System.out.println(listEntity.toString());
        List<ProductDetail> listCopy = new ArrayList<>();
        int n = listEntity.size();
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (listEntity.get(i).getColor().equals(listEntity.get(j).getColor())) {
                    if (listEntity.get(i).getRam().equals(listEntity.get(j).getRam())) {
                        if (listEntity.get(i).getMemmory().equals(listEntity.get(j).getMemmory())) {
                            listEntity.remove(i);
                            n--;
                            i--;
                        }
                    }
                }
            }
        }
        System.out.println(listEntity.toString());
        List<ProductDetailDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            product.setNote(product.getColor() + " - " + product.getRam() + " RAM - "
                    + product.getMemmory());
            list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
        });
        return list;
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetailByProductId(Long id) {
        List<ProductDetail> listEntity = getRepository().findAllProductDetailByProductId(id);
        List<ProductDetail> listCopy = new ArrayList<>();
        int n = listEntity.size();
        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (listEntity.get(i).getColor().equals(listEntity.get(j).getColor())) {
                    if (listEntity.get(i).getRam().equals(listEntity.get(j).getRam())) {
                        if (listEntity.get(i).getMemmory().equals(listEntity.get(j).getMemmory())) {
                            listEntity.remove(i);
                            n--;
                            i--;
                        }
                    }
                }
            }
        }
        System.out.println(listEntity.toString());
        List<ProductDetailDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            product.setNote(product.getColor() + " - " + product.getRam() + " RAM - "
                    + product.getMemmory());
            list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
        });
        return list;
    }

    @Override
    public List<ProductDetailDTO> getProductDetailByProductIdAndDetail(Long id, String ram, String color, String memmory) {
        List<ProductDetail> listEntity = getRepository()
                .findProductDetailByProductIdAndDetail(id, ram, color, memmory);
        System.out.println(listEntity.toString());
        List<ProductDetailDTO> list = new ArrayList<>();
        listEntity.stream().forEach(product -> {
            product.setNote(product.getColor() + " - " + product.getRam() + " RAM - "
                    + product.getMemmory());
            list.add(getMapper().toDto(product, new CycleAvoidingMappingContext()));
        });
        return list;
    }

    @Override
    public List<ProductDetail> getProductDetailEntityByProductIdAndDetail(Long id, String ram, String color, String memmory) {
        List<ProductDetail> listEntity = getRepository()
                .findProductDetailByProductIdAndDetail(id, ram, color, memmory);
        System.out.println(listEntity.toString());
        return listEntity;
    }

    @Override
    public Long countProductDetailByProductId(Long id) {
        Long count = getRepository().countProductDetailByProductId(id);
        System.out.println(count);
        return count;
    }

    @Override
    public Long countProductDetailByProductIdAndDetail(Long id, String ram, String color, String memmory) {
        Long count = getRepository().countProductDetailByProductIdAndDetail(
                id, ram, color, memmory);
        System.out.println(count);
        return count;
    }

    @Override
    public Long countImportProductThisMonth() {
        return getRepository().countImportProductThisMonth();
    }

    @Override
    public Long countImportProductInMonth(Integer month) {
        return getRepository().countImportProductInMonth(month);
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
