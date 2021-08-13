package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="hoadon")
@ToString
public class Invoice extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MAHD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "date is required")
    @Column(name = "NGAYDAT", nullable = false)
    private Date date;

    @NotBlank(message = "amount is required")
    @Column(name = "THANHTIEN", nullable = false)
    private Double amount;

    @OneToOne
    @JoinColumn(name = "MAPD")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;
}
