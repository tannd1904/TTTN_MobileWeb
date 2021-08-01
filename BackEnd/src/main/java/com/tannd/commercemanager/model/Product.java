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
@Table(name="products")
@ToString
public class Product extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "productName is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "URL is required")
    @Column(name = "url", nullable = false)
    private String url;

    @NotBlank(message = "Intro is required")
    @Column(name = "intro", nullable = false)
    private String intro;

    @Column(name = "promo", nullable = false)
    private String promo;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductDetail> productDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Review> reviews;

}
