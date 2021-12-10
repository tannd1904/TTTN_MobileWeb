package com.tannd.commercemanager.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AccessoryDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer status;

    private String image;

    private Double price;

    private Long accessoryCateId;

    private List<PromotionDTO> promotions;

}
