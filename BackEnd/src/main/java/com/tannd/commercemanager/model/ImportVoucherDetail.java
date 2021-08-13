package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "ct_phieunhap")
@ToString
public class ImportVoucherDetail extends AuditableEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACTPN", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPN")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ImportVoucher importVoucher;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADONGSP")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @OneToMany(mappedBy = "importVoucherDetail", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductDetail> productDetails;

    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;

    @Column(name = "GIA", nullable = false)
    private Double price;
}
