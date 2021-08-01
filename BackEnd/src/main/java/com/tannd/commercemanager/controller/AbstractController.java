package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.services.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
public class AbstractController<S extends AbstractService, D, E> {

    protected S service;

    public S getService() {
        return service;
    }

    public void initService(S service) {
        this.service = service;
    }

    public void initService() {}

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        System.out.println("Get All worked");
        var response = getService().findAll();
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Get All OK",
                response));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        System.out.println("Get Id worked with id: " + id);
        System.out.println(getService().toString());
        var dto = (D) getService().findById(id.longValue());
        if (dto == null) {
            return ResponseEntity.ok(new CustomResponse(404, "Resource Not Found",
                    null));
        }
        System.out.println(dto.toString());
        return ResponseEntity.ok(new CustomResponse(200, "Request Get By Id OK",
                dto));
    }
}
