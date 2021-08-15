package com.tannd.commercemanager.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ReturnDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Date date;

    private Long productDetailId;

    private Long orderId;

    private Long employeeId;
}
