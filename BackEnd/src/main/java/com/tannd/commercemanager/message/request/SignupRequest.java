package com.tannd.commercemanager.message.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tannd.commercemanager.validation.email.ValidEmail;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SignupRequest {

    @NotBlank(message = "password is required")
    @ValidEmail
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "role is required")
    private String role;

    @NotBlank(message = "name is required")
    private String firstName;

    @NotBlank(message = "name is required")
    private String lastName;

    @NotBlank(message = "gender is required")
    private String gender;

//    @NotBlank(message = "birthday is required")
    @DateTimeFormat
    private Date birthday;

    @NotBlank(message = "Email is required")
    private String address;

    @NotBlank(message = "phone is required")
    private String phone;

}
