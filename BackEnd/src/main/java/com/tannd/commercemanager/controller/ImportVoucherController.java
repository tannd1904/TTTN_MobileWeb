package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.ImportVoucherMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.ImportVoucherService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/import-voucher")
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
        initMapper();
        return mapper;
    }


}
