package com.tannd.commercemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDetailDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer quantity;

    private Long productDetailId;

    private Long cartId;
}
