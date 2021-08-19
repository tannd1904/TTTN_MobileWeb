package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.CategoryDTO;
import com.tannd.commercemanager.maper.CategoryMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.request.CategoryRequest;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/category")
public class CategoryController extends AbstractController<CategoryService, CategoryMapper, CategoryDTO, Category> {

    @Autowired
    CategoryService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public CategoryService getService() {
        initService();
        return service;
    }

    private CategoryMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public CategoryMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllCategory() {
        return getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return getById(id);
    }
    
    private boolean checkParameter(CategoryDTO dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        var list = getService().findAll();
        for (CategoryDTO cate :
                list) {
            if (cate.getName().equals(dto.getName())) {
                System.out.println("Duplicate Category Name");
                return false;
            }
        }
        return true;
    }

    @Transactional
    @PostMapping("/add-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO dto) {
        System.out.println(dto.toString());
        Category entity = new Category();
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Category name has been existed!",
                    null));
        }
        entity.setName(dto.getName());
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        CategoryDTO respone = new CategoryDTO();
        respone.setId(entity.getId());
        respone.setName(entity.getName());
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                respone));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return deleteById(id);
    }
}
