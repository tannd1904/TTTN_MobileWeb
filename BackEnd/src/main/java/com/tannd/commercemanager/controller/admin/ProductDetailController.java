package com.tannd.commercemanager.controller.admin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tannd.commercemanager.controller.AbstractController;
import com.tannd.commercemanager.dto.ProductDetailDTO;
import com.tannd.commercemanager.message.request.ProductDetailRequest;
import com.tannd.commercemanager.message.response.MessageResponse;
import com.tannd.commercemanager.model.Product;
import com.tannd.commercemanager.model.ProductDetail;
import com.tannd.commercemanager.repository.ProductDetailRepository;
import com.tannd.commercemanager.repository.ProductRepository;
import com.tannd.commercemanager.services.ProductDetailService;
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
public class ProductDetailController extends AbstractController<ProductDetailService, ProductDetailDTO, ProductDetail> {

//    @Autowired
//    ServletContext context;
//
//    @Autowired
//    ProductDetailRepository productDetailRepository;
//
//    @Autowired
//    SizeRepository sizeRepository;
//
//    @Autowired
//    ColorRepository colorRepository;
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Autowired
//    ProductDetailService productDetailService;
//
//    @PostMapping("/add-product-detail")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> createProductDetail (@RequestParam("file") MultipartFile file,
//                                            @RequestParam("productDetail") String  productDetail) throws JsonParseException, JsonMappingException, Exception
//    {
//        System.out.println("Ok .............");
//        ProductDetailRequest productDetailRequest = new ObjectMapper().readValue(productDetail, ProductDetailRequest.class);
//        boolean isExit = new File(context.getRealPath("/Images/")).exists();
//        if (!isExit)
//        {
//            new File (context.getRealPath("/Images/")).mkdir();
//            System.out.println("mk dir.............");
//        }
//        String filename = file.getOriginalFilename();
//        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
//        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
//        try
//        {
//            System.out.println("Image");
//            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//
//
//        productDetailRequest.setImage(newFileName);
//        ProductDetail productDetail1 = new ProductDetail();
//        productDetail1.setQuantity(productDetailRequest.getQuantity());
//        productDetail1.setPrice(productDetailRequest.getPrice());
//        productDetail1.setImage(productDetailRequest.getImage());
//        Size size = sizeRepository.findSizeBySizeId(productDetailRequest.getSizeId());
//        productDetail1.setSize(size);
//        Color color = colorRepository.findColorByColorId(productDetailRequest.getColorId());
//        productDetail1.setColor(color);
//        Product product = productRepository.findProductByProductId(productDetailRequest.getProductId());
//        productDetail1.setProduct(product);
//        productDetailRepository.save(productDetail1);
//        if (productDetail1 != null)
//        {
//            return new ResponseEntity<MessageResponse>(new MessageResponse (""), HttpStatus.OK);
//        }
//        else
//        {
//            return new ResponseEntity<MessageResponse>(new MessageResponse("Product detail not saved"),HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/product-detail/{productId}")
//    @PreAuthorize("hasRole('EMPLOYEE')")
//    public ResponseEntity<?> getAllProductDetailByProductId (@PathVariable(value = "productId") String productId) throws ResourceNotFoundException {
//        System.out.println("aaa");
//        List<ProductDetailDTO> productDetailDTOList = productDetailService.getAllProductDetail(productId);
//        return ResponseEntity.ok().body(productDetailDTOList);
//    }

}
