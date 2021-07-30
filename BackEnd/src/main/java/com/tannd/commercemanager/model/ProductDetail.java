package com.tannd.commercemanager.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="product_details")
@ToString
public class ProductDetail extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "color")
    private String color;

    @Column(name = "cpu")
    private String cpu;

    @Column(name = "ram")
    private String ram;

    @Column(name = "screen")
    private String screen;

    @Column(name = "storage")
    private String storage;

    @Column(name = "exten_memory")
    private String extendMemory;

    @Column(name = "cam1")
    private String cam1;

    @Column(name = "cam2")
    private String cam2;

    @Column(name = "sim")
    private String sim;

    @Column(name = "connect")
    private String connect;

    @Column(name = "pin")
    private String pin;

    @Column(name = "os")
    private String os;

    @Column(name = "note")
    private String note;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pro_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;
}
