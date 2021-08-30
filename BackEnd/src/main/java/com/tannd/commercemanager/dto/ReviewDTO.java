package com.tannd.commercemanager.dto;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ReviewDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String content;

    private String image;

    private Integer rating;

    private Long orderId;

    private Long productId;

    private Long userId;
}
