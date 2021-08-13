package com.tannd.commercemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String content;

    private String image;

    private Long orderId;

    private Long productId;
}
