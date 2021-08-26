package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="phieudat")
@ToString
public class Order extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MAPD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name of Receiver is required")
    @Column(name = "HOTEN", nullable = false)
    private String nameOfReceiver;

    @NotBlank(message = "addressOfReceiver is required")
    @Column(name = "DIACHI", nullable = false)
    private String addressOfReceiver;

    @NotBlank(message = "phoneOfReceiver is required")
    @Column(name = "SDT", nullable = false)
    private String phoneOfReceiver;

    @Column(name = "NGAYDAT", nullable = false)
    private Date dateOfOrder;

    @Column(name = "GHICHU", nullable = false)
    private String note;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> listOrderDetails;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Return aReturn;

    @OneToOne(mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Invoice invoice;

    @OneToOne(mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Review review;

}
