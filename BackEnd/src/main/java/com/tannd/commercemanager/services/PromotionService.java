package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.InvoiceDTO;
import com.tannd.commercemanager.dto.PromotionDTO;
import com.tannd.commercemanager.model.Invoice;
import com.tannd.commercemanager.model.Promotion;

import java.util.List;

public interface PromotionService extends AbstractService<PromotionDTO, Promotion>{
  List<PromotionDTO> getByProductId(Long id);
  List<PromotionDTO> getByAccessoryId(Long id);
}
