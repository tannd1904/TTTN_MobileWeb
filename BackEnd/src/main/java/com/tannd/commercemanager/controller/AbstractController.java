package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.services.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AbstractController<S extends AbstractService, D, E> {

    protected S service;

    public S getService() {
        return service;
    }

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        System.out.println("Get All worked");
        List<D> list = getService().findAll();
        return ResponseEntity.ok(new CustomResponse(200, "Request OK",
                list));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        System.out.println("Get Id worked with id: " + id);
        D dto = (D) getService().findById(id);
        return ResponseEntity.ok(new CustomResponse(200, "Request OK",
                dto));
    }
}
