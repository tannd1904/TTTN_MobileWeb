package com.tannd.commercemanager.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String password;

    private String phone;

    private String address;

    private Integer status;

    private String remember_token;

    private String reset_token;

    private List<OrderDTO> orders;

    private List<ReviewDTO> reviews;
}
