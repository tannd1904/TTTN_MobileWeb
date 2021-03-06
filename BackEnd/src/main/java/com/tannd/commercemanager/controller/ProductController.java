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
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProducts() {
        return getAll();
    }

    @GetMapping("/get-all-imported")
    public ResponseEntity<?> getAllProductsImported() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get All Products Imported",
                (getService().getAllProductImported())));
    }

    @GetMapping("/get-top-4-new")
    public ResponseEntity<?> getTop4NewProduct() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Top 4 New Product",
                (getService().getTop8ProductNewArrival())));
    }

    @GetMapping("/get-all-desc")
    public ResponseEntity<?> getAllProductDesc() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get All Product Desc By Price",
                (getService().getAllProductDesc())));
    }

    @GetMapping("/get-all-asc")
    public ResponseEntity<?> getAllProductAsc() {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get All Product Asc By Price",
                (getService().getAllProductAsc())));
    }

    @GetMapping("/get-by-category-id/{id}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Product Asc By Category Id",
                (getService().getByCategoryId(id))));
    }

    @GetMapping("/get-imported-by-category-id/{id}")
    public ResponseEntity<?> getImportedProductByCategoryId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Imported Product Asc By Category Id",
                (getService().getImportedByCategoryId(id))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return getById(id);
    }

    @GetMapping("/search-by-name/{name}")
    public ResponseEntity<?> searchByName(@PathVariable String name) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Search Product By Name",
                (getService().searchProductByName(name))));
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
        entity.setPromotion(0.0);
        var category = categoryService.findEntityById(dto.getCategoryId());
        entity.setCategory(category);
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        System.out.println(entity.toString());
//        ProductDTO response = new ProductDTO();
//        response.setId(entity.getId()).setName(entity.getName()).setDescription(entity.getDescription())
//                .setPrice(entity.getPrice()).setImage(entity.getImage()).setStatus(entity.getStatus())
//                .setType(entity.getType()).setCategoryId(entity.getCategory().getId());
        ProductDTO response = getMapper().toDtoWithoutList(entity, new CycleAvoidingMappingContext());
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }

    @Transactional
    @PutMapping("/edit-product/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestParam("file") MultipartFile file,
                                           @RequestParam("product") String product) throws JsonProcessingException {
        ProductRequest dto = new ObjectMapper().readValue(product, ProductRequest.class);
        System.out.println("Edit Product Begin...");
        System.out.println(dto.toString());
        String newFileName = "";

        if (!file.isEmpty()) {
            boolean isExit = new File(context.getRealPath("/Images/Product")).exists();
            if (!isExit)
            {
                new File (context.getRealPath("/Images/Product")).mkdir();
                System.out.println("mk dir.............");
            }
            String filename = file.getOriginalFilename();
            newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
            File serverFile = new File (context.getRealPath("/Images/Product"+File.separator+newFileName));
            try
            {
                System.out.println("Image");
                FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            newFileName = dto.getImage();
        }

        Product entity = getService().findEntityById(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
        entity.setImage(newFileName);
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        var category = categoryService.findEntityById(dto.getCategoryId());
        entity.setCategory(category);

        System.out.println(entity.toString());
        ProductDTO response = getMapper().toDtoWithoutList(entity, new CycleAvoidingMappingContext());
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }

    @Transactional
    @PutMapping("/edit-product-2/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProductWithoutImage(@PathVariable Long id,
                                         @RequestParam("product") String product) throws JsonProcessingException {
        ProductRequest dto = new ObjectMapper().readValue(product, ProductRequest.class);
        System.out.println("Edit Product Begin...");
        System.out.println(dto.toString());
        String newFileName = "";

        Product entity = getService().findEntityById(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        var category = categoryService.findEntityById(dto.getCategoryId());
        entity.setCategory(category);

        System.out.println(entity.toString());
        ProductDTO response = getMapper().toDtoWithoutList(entity, new CycleAvoidingMappingContext());
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }

    @Transactional
    @PostMapping("/add-product2")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createProduct2(@RequestBody ProductDTO dto) throws JsonProcessingException {
//        ProductRequest dto = new ObjectMapper().readValue(product, ProductRequest.class);
//
//        System.out.println(dto.toString());
//
//        boolean isExit = new File(context.getRealPath("/Images/Product")).exists();
//        if (!isExit)
//        {
//            new File (context.getRealPath("/Images/Product")).mkdir();
//            System.out.println("mk dir.............");
//        }
//        String filename = file.getOriginalFilename();
//        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
//        File serverFile = new File (context.getRealPath("/Images/Product"+File.separator+newFileName));
//        try
//        {
//            System.out.println("Image");
//            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

        Product entity = new Product();
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Product name has been existed!",
                    null));
        }
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStatus(dto.getStatus());
//        entity.setImage(newFileName);
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        entity.setType(dto.getType());
        var category = categoryService.findEntityById(dto.getCategoryId());
        entity.setCategory(category);
        entity.setPromotion(0.0);
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        System.out.println(entity.toString());
//        ProductDTO response = new ProductDTO();
//        response.setId(entity.getId()).setName(entity.getName()).setDescription(entity.getDescription())
//                .setPrice(entity.getPrice()).setImage(entity.getImage()).setStatus(entity.getStatus())
//                .setType(entity.getType()).setCategoryId(entity.getCategory().getId());
        ProductDTO response = new ProductDTO();
        var mapper = getMapper();
        System.out.println(mapper.toString());
        response = mapper.toDtoWithoutList(entity, new CycleAvoidingMappingContext());
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
