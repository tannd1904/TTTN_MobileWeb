package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportVoucherMapper extends AbstractMapper<ImportVoucherDTO, ImportVoucher>{
    ImportVoucherMapper INSTANCE = Mappers.getMapper(ImportVoucherMapper.class);
}
