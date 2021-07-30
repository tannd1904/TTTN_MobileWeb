package com.tannd.commercemanager.controller.admin;

import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ImageDTO;
import com.tannd.commercemanager.model.Image;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/image")
@PreAuthorize("hasRole('ADMIN') or ('USER')")
public class ImageController extends AbstractController<ImageService, ImageDTO, Image> {

//    @Autowired
//    ServletContext context;
//    @Autowired
//    ProductDetailRepository productDetailRepository;
//
//
//    @GetMapping(path="/imageProduct/{id}")
//    public byte[] getPhoto(@PathVariable("id") Integer id) throws Exception{
//        ProductDetail productDetail   = productDetailRepository.findByProductDetailId(id).get();
//        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+productDetail.getImage()));
//    }
}
