package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="taikhoan")
@ToString
public class Account extends AuditableEntity {

    @Id
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANQ")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;
}
