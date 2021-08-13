package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReturnDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date date;

    private Long productDetailId;

    private Long orderId;

    private Long employeeId;
}
