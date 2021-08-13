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
@Table(name="dongsp")
@ToString
public class Product extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MADONGSP", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "productName is required")
    @Column(name = "TENDONGSP", nullable = false)
    private String name;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @Column(name = "HINHANH", nullable = true)
    private String image;

    @Column(name = "MOTA", nullable = true)
    private String description;

    @Column(name = "LOAI", nullable = true)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAHANG")
    @EqualsAndHashCode.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ImportVoucherDetail> importVoucherDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Wishlist> wishlists;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviews;

}
