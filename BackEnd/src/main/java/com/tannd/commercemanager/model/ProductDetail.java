package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sanpham")
@ToString
@Accessors(chain = true)
public class ProductDetail extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MASP", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SERIAL")
    private String serial;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "CPU")
    private String cpu;

    @Column(name = "RAM")
    private String ram;

    @Column(name = "SCREEN")
    private String screen;

    @Column(name = "MEMMORY")
    private String memmory;

    @Column(name = "CAMERA")
    private String camera;

    @Column(name = "PIN")
    private String pin;

    @Column(name = "OS")
    private String os;

    @Column(name = "GHICHU")
    private String note;

    @Column(name = "GIA", nullable = false)
    private Double price;

    @Column(name = "TRANGTHAI", nullable = false)
    private Boolean status;

    @OneToOne(mappedBy = "productDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderDetail orderDetail;

    @OneToOne(mappedBy = "productDetail")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Return aReturn;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MACTPN")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ImportVoucherDetail importVoucherDetail;
}
