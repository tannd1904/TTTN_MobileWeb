package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WishlistDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer quantity;

    private Long userId;

    private Long productId;

}