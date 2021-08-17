package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.ImportVoucherDetailMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.ImportVoucherDetailService;
import com.tannd.commercemanager.services.ImportVoucherService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        initMapper();
        return mapper;
    }
}
