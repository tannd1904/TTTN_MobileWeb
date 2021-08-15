package com.tannd.commercemanager.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.maper.ProductMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.request.ProductRequest;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.ProductService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController
        extends AbstractController<ProductService, ProductMapper, ProductDTO, Product> {

    @Autowired
    ProductService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ProductService getService() {
        initService();
        return service;
    }

    private ProductMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ProductMapper getMapper() {
        initMapper();
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProducts() {
        return getAll();
    }

    @GetMapping("/get-top-4-new")
    public ResponseEntity<?> getTop4NewProduct() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Top 4 New Product",
                (getService().getTop4ProductNewArrival())));
    }

    @Transactional
    @PostMapping("/add-product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestParam("file") MultipartFile file,
                                           @RequestParam("product") String product) throws JsonProcessingException {
        ProductRequest dto = new ObjectMapper().readValue(product, ProductRequest.class);

        System.out.println(dto.toString());

        boolean isExit = new File(context.getRealPath("/Images/Product")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/Product")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/Product"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }

        Product entity = new Product();
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Product name has been existed!",
                    null));
        }
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
        entity.setImage(newFileName);
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        var category = categoryService.findEntityById(dto.getCategoryId());
        entity.setCategory(category);
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        System.out.println(entity.toString());
        ProductDTO response = new ProductDTO();
        response.setId(entity.getId()).setName(entity.getName()).setDescription(entity.getDescription())
                .setPrice(entity.getPrice()).setImage(entity.getImage()).setStatus(entity.getStatus())
                .setType(entity.getType()).setCategoryId(entity.getCategory().getId());
//        ProductDTO response = getMapper().toDtoWithoutList(entity, new CycleAvoidingMappingContext());
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }

    private boolean checkParameter(ProductDTO dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        var list = getService().findAll();
        for (ProductDTO pro :
                list) {
            if (pro.getName().equals(dto.getName())) {
                System.out.println("Duplicate Product Name");
                return false;
            }
        }
        return true;
    }

    private boolean checkParameter(ProductRequest dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        var list = getService().findAll();
        for (ProductDTO pro :
                list) {
            if (pro.getName().equals(dto.getName())) {
                System.out.println("Duplicate Product Name");
                return false;
            }
        }
        return true;
    }

    @Autowired
    CategoryService categoryService;

    @Autowired
    ServletContext context;

}
