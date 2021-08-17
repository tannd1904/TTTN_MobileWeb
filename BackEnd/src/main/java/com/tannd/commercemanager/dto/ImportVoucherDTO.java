package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.ImportVoucherDetail;
import lombok.*;
import lombok.experimental.Accessors;
import uk.co.jemos.podam.common.PodamExclude;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ImportVoucherDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date date;

    private Long employeeId;

    @PodamExclude
    private List<ImportVoucherDetailDTO> importVoucherDetails;
}
