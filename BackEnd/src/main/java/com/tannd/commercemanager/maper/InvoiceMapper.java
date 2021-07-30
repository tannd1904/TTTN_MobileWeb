package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvoiceMapper extends AbstractMapper<InvoiceDTO, Invoice>{
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);
}
