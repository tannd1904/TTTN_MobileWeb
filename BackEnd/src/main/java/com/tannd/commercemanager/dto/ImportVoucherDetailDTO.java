package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ImportVoucherDetailDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long importVoucherId;

    private Long productId;

    private List<ProductDetailDTO> productDetails;

    private Integer quantity;

    private Double price;
}
