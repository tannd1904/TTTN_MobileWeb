package com.tannd.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="invoice")
@ToString
public class Invoice extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "date is required")
    @Column(name = "date", nullable = false)
    private Date date;

    @NotBlank(message = "amount is required")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
