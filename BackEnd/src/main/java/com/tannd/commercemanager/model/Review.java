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
@Table(name= "danhgia")
@ToString
public class Review extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MADG", nullable = false)
    private Long id;

    @NotBlank(message = "Content is required")
    @Column(name = "NOIDUNG")
    private String content;

    @Column(name = "HINHANH")
    private String image;

    @Column(name = "MUCDO")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONGSP")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
