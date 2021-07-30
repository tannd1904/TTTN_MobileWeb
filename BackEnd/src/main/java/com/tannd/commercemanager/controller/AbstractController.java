package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.services.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        List<D> list = getService().findAll();
        return ResponseEntity.ok(new CustomResponse(200, "Request OK",
                list));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(Long id) {
        D dto = (D) getService().findById(id);
        return ResponseEntity.ok(new CustomResponse(200, "Request OK",
                dto));
    }
}
