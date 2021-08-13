package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.ImportVoucherDetail;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImportVoucherDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date date;

    private Long employeeId;

    private List<ImportVoucherDetail> importVoucherDetails;
}
