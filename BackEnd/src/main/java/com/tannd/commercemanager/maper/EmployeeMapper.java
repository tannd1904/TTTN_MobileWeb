package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.dto.EmployeeDTO;
import com.tannd.commercemanager.dto.WishlistDTO;
import com.tannd.commercemanager.model.Employee;
import com.tannd.commercemanager.model.Wishlist;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper extends AbstractMapper<EmployeeDTO, Employee>{
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
}
