package com.tannd.commercemanager.controller.admin;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.message.request.CategoryRequest;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class CategoryController extends AbstractController<CategoryService, CategoryDTO, Category> {

//    @Autowired
//    CategoryService categoryService;
//
//    @PostMapping("/add-category")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
//        CategoryDTO categoryDTO = categoryService.save(categoryRequest);
//        return ResponseEntity.ok().body(categoryDTO);
//    }
//
//    @GetMapping("/category")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> getCategory() {
//        List<CategoryDTO> categoryDTOList = categoryService.getAllCategory();
//        return ResponseEntity.ok().body(categoryDTOList);
//    }
}
