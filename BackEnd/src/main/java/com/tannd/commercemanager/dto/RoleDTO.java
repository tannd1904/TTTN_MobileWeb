package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.ERole;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class RoleDTO extends AbstractDTO{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private ERole name;

    private List<AccountDTO> accountDTOs;
}
