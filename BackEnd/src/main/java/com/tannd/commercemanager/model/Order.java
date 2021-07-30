package com.tannd.commercemanager.model;

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
@Table(name="orders")
@ToString
public class Order extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "ID is required")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Name of Receiver is required")
    @Column(name = "name_receiver", nullable = false)
    private String nameOfReceiver;

    @NotBlank(message = "addressOfReceiver is required")
    @Column(name = "address_receiver", nullable = false)
    private String addressOfReceiver;

    @NotBlank(message = "phoneOfReceiver is required")
    @Column(name = "phone_receiver", nullable = false)
    private String phoneOfReceiver;

    @NotBlank(message = "dateOfOrder is required")
    @Column(name = "date_order", nullable = false)
    private Date dateOfOrder;

    @NotBlank(message = "Type is required")
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "Note is required")
    @Column(name = "note", nullable = false)
    private String note;

    @NotBlank(message = "status is required")
    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> orderDetails;

}
