package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.maper.AbstractMapper;
import com.tannd.commercemanager.maper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.services.AbstractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
public class AbstractController<S extends AbstractService, M extends AbstractMapper, D, E> {

    protected S service;
    protected M mapper;

    public S getService() {
        return service;
    }

    public void initService(S service) {
        this.service = service;
    }

    public void initService() {}

    public M getMapper() {
        return mapper;
    }

    public void initMapper(M mapper) {
        this.mapper = mapper;
    }

    public void initMapper() {}

    public ResponseEntity getAll() {
        System.out.println("Get All worked");
        var response = getService().findAll();
        System.out.println(response);
        return ResponseEntity.ok(new CustomResponse(200, "Request Get All OK",
                response));
    }

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

    public ResponseEntity create(@RequestBody D dto) {
        var entity = getMapper().toEntity(dto, new CycleAvoidingMappingContext());
        try {
            getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomResponse(500, "Request Not Ok",
                    null));
        }
        var respone = getMapper().toDto(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                respone));
    }

    public ResponseEntity deleteById(@PathVariable Long id) {
        System.out.println("Delete worked with id: " + id);
        System.out.println(getService().toString());
        var dto = (D) getService().findById(id.longValue());
        if (dto == null) {
            return ResponseEntity.ok(new CustomResponse(404, "Resource Not Found",
                    null));
        }
        getService().delete(id);
        return ResponseEntity.ok().body(new CustomResponse(200, "Delete Request OK",
                dto));
    }

    public ResponseEntity update(@PathVariable Long id, @RequestBody D dto) {
        System.out.println("Update worked with id: " + id);
        System.out.println(getService().toString());
        var obj = (D) getService().findById(id.longValue());
        if (obj == null) {
            return ResponseEntity.ok(new CustomResponse(404, "Resource Not Found",
                    null));
        }
        return ResponseEntity.ok().body(new CustomResponse(200, "Update Request OK",
                obj));
    }
}
