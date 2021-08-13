package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="nhanvien")
@ToString
@ApiModel(value = "All details about the users.")
@Accessors(chain = true)
public class Employee extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MANV", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "FName is required")
    @Column(name = "HO", nullable = false)
    private String firstname;


    @NotBlank(message = "LName is required")
    @Column(name = "TEN", nullable = false)
    private String lastname;

    @NotBlank(message = "Email is required")
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank(message = "Phone is required")
    @Column(name = "SDT", nullable = false)
    private String phone;

    @NotBlank(message = "Address is required")
    @Column(name = "DIACHI", nullable = false)
    private String address;

    @NotBlank(message = "Birthday is required")
    @Column(name = "NGAYSINH", nullable = false)
    private Date birthday;

    @NotBlank(message = "Gender is required")
    @Column(name = "GIOITINH", nullable = false)
    private String gender;

//    @Column(name = "remember_token", nullable = false)
//    private String remember_token;
//
//    @Column(name = "reset_token", nullable = false)
//    private String reset_token;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ImportVoucher> importVouchers;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Return> returns;


}
