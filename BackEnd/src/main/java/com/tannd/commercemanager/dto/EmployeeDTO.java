package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.ImportVoucher;
import com.tannd.commercemanager.model.Return;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class EmployeeDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private String address;

    private String gender;

//    @Column(name = "remember_token", nullable = false)
//    private String remember_token;
//
//    @Column(name = "reset_token", nullable = false)
//    private String reset_token;

    private List<OrderDTO> orders;

    private List<ImportVoucherDTO> importVouchers;
//
    private List<ReturnDTO> returns;
}
