package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.ImportVoucherMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.message.response.CustomResponse;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.repository.EmployeeRepository;
import com.tannd.commercemanager.services.EmployeeService;
import com.tannd.commercemanager.services.ImportVoucherService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/import")
public class ImportVoucherController extends
        AbstractController<ImportVoucherService, ImportVoucherMapper,
                ImportVoucherDTO, ImportVoucher> {

    @Autowired
    ImportVoucherService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public ImportVoucherService getService() {
        initService();
        return service;
    }

    private ImportVoucherMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public ImportVoucherMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllImports() {
        return getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getImport(@PathVariable Long id) {
        return getById(id);
    }

    private boolean checkParameter(ImportVoucherDTO dto) {
        if (Objects.isNull(dto)) {
            return false;
        }
        return true;
    }

    @Autowired
    EmployeeService employeeService;

    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createImport(@RequestBody ImportVoucherDTO dto) {
        if (!checkParameter(dto)) {
            return ResponseEntity.ok().body(new CustomResponse(400, "Invalid DTO!",
                    null));
        }
        System.out.println(dto.toString());
        ImportVoucher entity = new ImportVoucher();
//        entity = getMapper().toEntity(dto, new CycleAvoidingMappingContext());
        entity.setDate(dto.getDate());
        var employee = employeeService.findEntityById(dto.getEmployeeId());
        entity.setEmployee(employee);
        System.out.println(entity.toString());
//        entity = getService().save(entity);
        try {
            entity = getService().save(entity);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new CustomResponse(500, "Internal exception!",
                    null));
        }
        var response = getMapper().toDtoWithoutDetails(entity, new CycleAvoidingMappingContext());
        return ResponseEntity.ok(new CustomResponse(200, "Request Post OK",
                response));
    }


}
