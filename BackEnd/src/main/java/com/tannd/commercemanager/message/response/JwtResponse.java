package com.tannd.commercemanager.message.response;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String phone;
    private String address;
    private String name;
    private Integer gender;
    private Date birthday;
    private String role;
    private int status;
    private String message;
}
