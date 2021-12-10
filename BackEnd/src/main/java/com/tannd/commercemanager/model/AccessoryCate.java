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
@Table(name="loai_pk")
@ToString
public class AccessoryCate extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MALOAIPK", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Accessory Category name is required")
    @Column(name = "TENLOAIPK", nullable = false)
    private String name;

//    @NotBlank(message = "Category url is required")
//    @Column(name = "slug", nullable = false)
//    private String url;
//
//    @NotBlank(message = "Parent ID is required")
//    @Column(name = "parent_id", nullable = false)
//    private Long parentId;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Accessory> accessories;
}
