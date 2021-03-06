package com.tannd.commercemanager.dto;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class OrderDetailDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String productDetailSerial;

    private Long productId;

    private Long orderId;
}
