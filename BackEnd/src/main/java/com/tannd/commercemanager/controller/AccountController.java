package com.tannd.commercemanager.controller;

import com.tannd.commercemanager.dto.AccountDTO;
import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.AccountMapper;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.services.AccountService;
import com.tannd.commercemanager.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController extends AbstractController<AccountService, AccountMapper, AccountDTO, Account> {

    @Autowired
    AccountService thisService;

    @Override
    public void initService() {
        service = thisService;
    }

    @Override
    public AccountService getService() {
        initService();
        return service;
    }

    private AccountMapper thisMapper;

    @Override
    public void initMapper() {
        mapper = thisMapper;
    }

    @Override
    public AccountMapper getMapper() {
        initMapper(thisMapper.INSTANCE);
        return mapper;
    }
}
