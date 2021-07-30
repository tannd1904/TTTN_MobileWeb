package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nameOfReceiver;

    private String addressOfReceiver;

    private String phoneOfReceiver;

    private Date dateOfOrder;

    private String type;

    private String note;

    private Integer status;

    private Long userId;

    private List<OrderDetailDTO> orderDetails;

}
