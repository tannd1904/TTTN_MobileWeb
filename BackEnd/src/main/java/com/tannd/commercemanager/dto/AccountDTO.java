package com.tannd.commercemanager.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountDTO extends AbstractDTO{

    private static final long serialVersionUID = 1L;

    private String email;

    private String password;

    private Long roleId;
}
