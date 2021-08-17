package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.*;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class OrderDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nameOfReceiver;

    private String addressOfReceiver;

    private String phoneOfReceiver;

    private Date dateOfOrder;

//    @NotBlank(message = "Type is required")
//    @Column(name = "type", nullable = false)
//    private String type;

    private String note;

    private Integer status;

    private Long userId;

    private Long employeeId;

    private List<ImportVoucherDetailDTO> importVoucherDetails;

    private Long returnId;

    private Long invoiceId;

    private Long reviewId;

}
