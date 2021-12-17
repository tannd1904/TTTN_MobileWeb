package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.dto.PropertyDTO;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.model.Property;

import java.util.List;

public interface PropertyService extends AbstractService<PropertyDTO, Property>{
  List<PropertyDTO> findByProductId(Long productId);
}
