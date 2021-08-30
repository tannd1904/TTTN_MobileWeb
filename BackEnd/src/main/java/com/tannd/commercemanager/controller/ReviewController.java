package com.tannd.commercemanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.ReviewDTO;
import com.tannd.commercemanager.dto.UserDTO;
import com.tannd.commercemanager.maper.ReviewMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.request.ProductRequest;
import com.tannd.commercemanager.message.request.ReviewRequest;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.services.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@RestController
@RequestMapping("/api/review")
public class ReviewController extends AbstractController<ReviewService, ReviewMapper, ReviewDTO, Review> {

    @Autowired
    ReviewService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ReviewService getService() {
        initService();
        return service;
    }

    private ReviewMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ReviewMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-by-product-id/{id}")
    public ResponseEntity<?> getAllByProductId(@PathVariable Long id) {
        System.out.println("Get Reviews By Product Id " + id);
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Review By Product Id OK",
                getService().getAllByProductId(id)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        System.out.println("Get Review By Id " + id);
        return getById(id);
    }

    @Autowired
    ServletContext context;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Transactional
    @PostMapping("/add-review")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createProduct(@RequestParam("file") MultipartFile file,
                                           @RequestParam("review") String review) throws JsonProcessingException {
        ReviewRequest dto = new ObjectMapper().readValue(review, ReviewRequest.class);

        System.out.println(dto.toString());

        boolean isExit = new File(context.getRealPath("/Images/Review")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/Review")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/Review"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
        } catch(Exception e) {
            e.printStackTrace();
        }

        Review entity = new Review();

        entity.setContent(dto.getContent());
        entity.setImage(newFileName);
        entity.setRating(dto.getRating());
        entity.setOrder(orderService.findEntityById(dto.getOrderId()));
        entity.setProduct(productService.findEntityById(dto.getProductId()));
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        System.out.println(entity.toString());
        ReviewDTO response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Post Review OK",
                response));
    }
}
