package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportVoucherDetailMapper extends
        AbstractMapper<ImportVoucherDetailDTO, ImportVoucherDetail>{
    ImportVoucherDetailMapper INSTANCE = Mappers.getMapper(ImportVoucherDetailMapper.class);

    @Mappings({
            @Mapping(source = "importVoucher.id", target = "importVoucherId"),
            @Mapping(source = "product.id", target = "productId")
    })
    ImportVoucherDetailDTO toDtoWithImportIdAndProductId(ImportVoucherDetail entity, @Context
                                             CycleAvoidingMappingContext context);

    @Override
    default ImportVoucherDetailDTO toDto(ImportVoucherDetail entity, @Context
                                         CycleAvoidingMappingContext context) {
        return toDtoWithImportIdAndProductId(entity, context);
    }
}
