package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ImageDTO extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String path;

    private Long productId;
}
