package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="phukien")
@ToString
public class Accessory extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MAPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "productName is required")
    @Column(name = "TENPK", nullable = false)
    private String name;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @Column(name = "HINHANH", nullable = true)
    private String image;

    @Column(name = "GIA", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MALOAIPK")
    @EqualsAndHashCode.Exclude
    private AccessoryCate category;

    @OneToMany(mappedBy = "accessory", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Promotion> promotions;
}
