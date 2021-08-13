package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private List<ProductDTO> products;
}
