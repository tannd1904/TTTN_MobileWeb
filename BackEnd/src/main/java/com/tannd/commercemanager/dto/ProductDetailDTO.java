package com.tannd.commercemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetailDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String color;

    private String cpu;

    private String ram;

    private String screen;

    private String storage;

    private String extendMemory;

    private String cam1;

    private String cam2;

    private String sim;

    private String connect;

    private String pin;

    private String os;

    private String note;

    private Integer quantity;

    private Double price;

    private Long productId;
}
