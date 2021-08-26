package com.tannd.commercemanager.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.ProductDetailMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.request.ProductDetailRequest;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.message.response.MessageResponse;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.ImportVoucherDetailService;
import com.tannd.commercemanager.services.ProductDetailService;
import com.tannd.commercemanager.services.ProductService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/product-detail")
public class ProductDetailController extends AbstractController<ProductDetailService, ProductDetailMapper, ProductDetailDTO, ProductDetail> {

    @Autowired
    ProductDetailService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ProductDetailService getService() {
        initService();
        return service;
    }

    private ProductDetailMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProductDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Autowired
    ImportVoucherDetailService importVoucherDetailService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        return getAll();
    }

    @GetMapping("/get-by-product-id/{id}")
    public ResponseEntity<?> getProductDetailByProductId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Product Detail By Product Id",
                (getService().getProductDetailByProductId(id))));
    }

    @GetMapping("/get-same-detail/{id}/{ram}/{color}/{memmory}")
    public ResponseEntity<?> getProductDetailByProductIdAndDetail(
            @PathVariable("id") Long id, @PathVariable("ram") String ram,
            @PathVariable("color") String color, @PathVariable("memmory") String memmory) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Product Detail By Product Id",
                (getService().getProductDetailByProductIdAndDetail(id, ram, color, memmory))));
    }

    @GetMapping("/count-by-product-id/{id}")
    public ResponseEntity<?> countProductDetailByProductId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Product Detail By Product Id",
                (getService().countProductDetailByProductId(id))));
    }

    @GetMapping("/count-same-detail/{id}/{ram}/{color}/{memmory}")
    public ResponseEntity<?> countProductDetailByProductIdAndDetail(
            @PathVariable("id") Long id, @PathVariable("ram") String ram,
            @PathVariable("color") String color, @PathVariable("memmory") String memmory) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Count Product Detail By Product Id",
                (getService().countProductDetailByProductIdAndDetail(id, ram, color, memmory))));
    }

    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProductDetail(@RequestBody ProductDetailDTO dto) {
        System.out.println("Add Product detail start");
        System.out.println(dto.toString());
        ProductDetail entity = new ProductDetail();
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Product detail Serial has been existed!",
                    null));
        }
        entity = getMapper().toEntity(dto, new CycleAvoidingMappingContext());
        entity.setPrice(0.0);
        entity.setStatus(false);
        entity.setImportVoucherDetail(importVoucherDetailService.findEntityById(dto.getImportVoucherDetailId()));
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        System.out.println(entity.toString());
        ProductDetailDTO response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        System.out.println(response.toString());
        return ResponseEntity.ok(new CustomResponse(200, "Request Post Product Detail OK",
                response));

    }

    @Transactional
    @PostMapping("/add-list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createListProductDetail(@RequestBody List<ProductDetailDTO> dto) {
        ProductDetail entity = new ProductDetail();
        for (ProductDetailDTO pro :
                dto) {
            this.createProductDetail(pro);
        }
        return ResponseEntity.ok(new CustomResponse(200, "Request Post List Product Detail OK",
                dto));
    }

    private boolean checkParameter(ProductDetailDTO dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        var list = getService().findAll();
        for (ProductDetailDTO pro : list) {
            if (pro.getSerial().equals(dto.getSerial())) {
                return false;
            }
        }
        return true;
    }

}
