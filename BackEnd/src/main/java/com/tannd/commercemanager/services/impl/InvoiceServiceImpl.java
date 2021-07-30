package com.tannd.commercemanager.services.impl;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.maper.InvoiceMapper;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.repository.InvoiceRepository;
import com.tannd.commercemanager.services.InvoiceService;

public class InvoiceServiceImpl extends AbstractServiceImpl<InvoiceRepository, InvoiceMapper, InvoiceDTO, Invoice>
        implements InvoiceService {
}
