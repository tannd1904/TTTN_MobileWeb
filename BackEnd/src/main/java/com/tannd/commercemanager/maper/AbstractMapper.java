package com.tannd.commercemanager.maper;

import com.tannd.commercemanager.maper.helper.CycleAvoidingMappingContext;
import org.mapstruct.Context;

public interface AbstractMapper <D, E>{

    E toEntity(D dto, @Context CycleAvoidingMappingContext context);

    D toDto(E entity, @Context CycleAvoidingMappingContext context);
}
