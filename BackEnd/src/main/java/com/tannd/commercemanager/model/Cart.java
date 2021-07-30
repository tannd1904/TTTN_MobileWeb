package com.tannd.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="cart")
@ToString
public class Cart extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "Id is required")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "quantity is required")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne
    @JoinTable(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CartDetail> cartDetails;

}
