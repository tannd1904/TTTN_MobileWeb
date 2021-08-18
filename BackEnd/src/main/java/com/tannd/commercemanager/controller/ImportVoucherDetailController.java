package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.ImportVoucherDetailMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.ImportVoucherDetailService;
import com.tannd.commercemanager.services.ImportVoucherService;
import com.tannd.commercemanager.services.InvoiceService;
import com.tannd.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/import-detail")
public class ImportVoucherDetailController extends
        AbstractController<ImportVoucherDetailService, ImportVoucherDetailMapper,
                ImportVoucherDetailDTO, ImportVoucherDetail> {

    @Autowired
    ImportVoucherDetailService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ImportVoucherDetailService getService() {
        initService();
        return service;
    }

    private ImportVoucherDetailMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ImportVoucherDetailMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllImpDetail() {
        return getAll();
    }

    @Autowired
    ProductService productService;

    @Autowired
    ImportVoucherService importVoucherService;

    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createImpDetail(@RequestBody ImportVoucherDetailDTO dto) {
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Invalid DTO!",
                    null));
        }
        System.out.println(dto.toString());
        ImportVoucherDetail entity = new ImportVoucherDetail();
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setProduct(productService.findEntityById(dto.getProductId()));
        System.out.println(entity.getProduct().getId());
        entity.setImportVoucher(importVoucherService.findEntityById(dto.getImportVoucherId()));
        System.out.println(entity.getImportVoucher().getId());
//        entity = getMapper().toEntity(dto, new CycleAvoidingMappingContext());
        System.out.println(entity.toString());
//        entity = getService().save(entity);
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Internal exception!",
                    null));
        }
        var response = getMapper().toDtoWithImportId(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }

    private boolean checkParameter(ImportVoucherDetailDTO dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        return true;
    }

}
