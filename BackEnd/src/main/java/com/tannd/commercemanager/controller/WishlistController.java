package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ProductDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.WishlistMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.User;
import com.tannd.commercemanager.model.Wishlist;
import com.tannd.commercemanager.services.ProductService;
import com.tannd.commercemanager.services.UserService;
import com.tannd.commercemanager.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/wish-list")
public class WishlistController extends AbstractController<WishlistService, WishlistMapper, WishlistDTO, Wishlist> {

    @Autowired
    WishlistService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public WishlistService getService() {
        initService();
        return service;
    }

    private WishlistMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public WishlistMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllWishList() {
        return getAll();
    }

    @GetMapping("/user-id/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(new CustomResponse(200, "Get Wish List By User Id",
                (getService().getByUserId(id))));
    }

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> createWishList(@RequestBody WishlistDTO dto) {
        System.out.println("Add WishList begin");
        System.out.println(dto.toString());
        Wishlist entity = new Wishlist();
        entity.setProduct(productService.findEntityById(dto.getProductId()));
        Long t = dto.getUserId();
        var user = userService.findEntityById(dto.getUserId());
        System.out.println(t + user.toString());
        entity.setUser(user);
        System.out.println(entity.getUser().toString());
        Wishlist temp = getService().getByProductId(entity.getProduct().getId());
        if (!Objects.isNull(temp)) {
            temp.setRemoval_flag(false);
            WishlistDTO response = getMapper().toDto(temp, new CycleAvoidingMappingContext());
            System.out.println(response);
            return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                    response));
        } else {
            try {
                entity.setRemoval_flag(false);
                entity = getService().save(entity);
            } catch (Exception e) {
                return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok" +
                        "\nMessage: " + e.getMessage(),
                        null));
            }
            WishlistDTO response = getMapper().toDto(entity, new CycleAvoidingMappingContext());
            System.out.println(response);
            return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                    response));
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWishListById(@PathVariable Long id) {
        try {
            getService().delete(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Request Not Ok" +
                    "\nMessage: " + e.getMessage(),
                    null));
        }
        var response = getService().findById(id);
        return ResponseEntity.ok(new CustomResponse(200, "Request Delete OK",
                response));
    }
}
