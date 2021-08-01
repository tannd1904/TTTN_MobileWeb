package com.tannd.commercemanager.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.maper.ProductDetailMapper;
import com.tannd.commercemanager.message.request.ProductDetailRequest;
import com.tannd.commercemanager.message.response.MessageResponse;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.ProductDetailService;
import com.tannd.commercemanager.services.ProductService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/product-detail")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
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
        initMapper();
        return mapper;
    }

}
