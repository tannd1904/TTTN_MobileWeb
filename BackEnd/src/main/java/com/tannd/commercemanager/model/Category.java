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
@Table(name="category")
@ToString
public class Category extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "CategoryId is required")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Category name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Category url is required")
    @Column(name = "slug", nullable = false)
    private String url;

    @NotBlank(message = "Parent ID is required")
    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;
}
