package com.tannd.commercemanager.services;

import com.tannd.commercemanager.dto.AccountDTO;
import com.tannd.commercemanager.dto.ImportVoucherDetailDTO;
import com.tannd.commercemanager.model.Account;
import com.tannd.commercemanager.model.ImportVoucherDetail;

import java.util.List;

public interface ImportVoucherDetailService extends AbstractService<ImportVoucherDetailDTO, ImportVoucherDetail> {
    List<ImportVoucherDetailDTO> getByImportId(Long id);
}
