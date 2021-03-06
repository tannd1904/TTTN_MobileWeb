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
@Table(name="ct_phieudat")
@ToString
public class OrderDetail extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MACTPD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "SERIAL")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDetail productDetail;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;
}
