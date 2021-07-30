package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String url;

    private String intro;

    private String promo;

    private String tag;

    private Integer status;

    private Long categoryId;

    private List<ProductDetailDTO> productDetails;

    private List<ImageDTO> images;

    private List<ReviewDTO> reviews;

}
