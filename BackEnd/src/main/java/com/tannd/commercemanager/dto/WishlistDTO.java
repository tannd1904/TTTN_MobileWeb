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
public class WishlistDTO extends AbstractDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long userId;

    private Long productId;

    private Boolean removal_flag;

}
