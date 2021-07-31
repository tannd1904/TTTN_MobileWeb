package com.tannd.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="detail_img")
@ToString
public class Image extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Path is required")
    @Column(name = "images_url")
    private String path;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
