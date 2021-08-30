package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.maper.ImageMapper;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.services.CategoryService;
import com.tannd.commercemanager.services.ImageService;
import com.tannd.commercemanager.services.ProductService;
import com.tannd.commercemanager.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/image")
public class ImageController extends AbstractController<ImageService, ImageMapper, ImageDTO, Image> {

    @Autowired
    ImageService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ImageService getService() {
        initService();
        return service;
    }

    private ImageMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ImageMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @Autowired
    ServletContext context;

    @Autowired
    ProductService productService;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Product product = productService.findEntityById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/Product/")+product.getImage()));
    }

    @GetMapping("/review/{id}")
    public byte[] getReviewPhoto(@PathVariable("id") Long id) throws Exception{
        Review product = reviewService.findEntityById(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/Review/")+product.getImage()));
    }
}
