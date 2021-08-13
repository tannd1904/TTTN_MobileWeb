package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.Order;
import com.tannd.commercemanager.model.Wishlist;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String address;

//    @Column(name = "remember_token", nullable = false)
//    private String remember_token;
//
//    @Column(name = "reset_token", nullable = false)
//    private String reset_token;

    private List<Order> orders;

    private Long wishlistId;
}
