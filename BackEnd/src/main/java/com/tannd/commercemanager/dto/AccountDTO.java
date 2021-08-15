package com.tannd.commercemanager.dto;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AccountDTO extends AbstractDTO{

    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    private Long roleId;
}
