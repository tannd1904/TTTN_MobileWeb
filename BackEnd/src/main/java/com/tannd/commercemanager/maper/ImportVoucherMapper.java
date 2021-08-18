package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.ImportVoucherDTO;
import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImportVoucherMapper extends AbstractMapper<ImportVoucherDTO, ImportVoucher>{
    ImportVoucherMapper INSTANCE = Mappers.getMapper(ImportVoucherMapper.class);

    @Mappings({
            @Mapping(source = "employee.id", target = "employeeId"),
            @Mapping(target = "importVoucherDetails", ignore = true)
    })
    ImportVoucherDTO toDtoWithoutDetails(ImportVoucher entity, @Context CycleAvoidingMappingContext context);

    @Mappings({
            @Mapping(source = "employee.id", target = "employeeId"),
            @Mapping(source = "importVoucherDetails", target = "importVoucherDetails"),
    })
    ImportVoucherDTO toDtoWithDetails(ImportVoucher entity, @Context CycleAvoidingMappingContext context);

//    @Override
//    default ImportVoucherDTO toDto(ImportVoucher entity, @Context CycleAvoidingMappingContext context) {
//        return toDtoWithoutDetails(entity, context);
//    }
    @Mappings({
            @Mapping(source = "employeeId", target = "employee.id")
    })
    ImportVoucher toEntityWithEmplId(ImportVoucherDTO dto, @Context CycleAvoidingMappingContext context);

    @Override
    default ImportVoucher toEntity(ImportVoucherDTO dto, @Context CycleAvoidingMappingContext context) {
        return toEntityWithEmplId(dto, context);
    }

}
