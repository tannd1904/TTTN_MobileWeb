package com.tannd.commercemanager.controller.admin;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.message.request.ProductRequest;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class ProductController extends AbstractController<ProductService, ProductDTO, Product> {

//    @Autowired
//    ProductService productService;
//
//    @PostMapping("/add-product")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
//        ProductDTO productDTO = productService.save(productRequest);
//        return ResponseEntity.ok().body(productDTO);
//    }
//
//    @GetMapping("/product")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> getProduct() {
//        List<ProductDTO> productDTOList = productService.getAllProduct();
//        return ResponseEntity.ok().body(productDTOList);
//    }
}
