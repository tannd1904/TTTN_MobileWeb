package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportVoucherDetailMapper extends
        AbstractMapper<ImportVoucherDetailDTO, ImportVoucherDetail>{
    ImportVoucherDetailMapper INSTANCE = Mappers.getMapper(ImportVoucherDetailMapper.class);
}
