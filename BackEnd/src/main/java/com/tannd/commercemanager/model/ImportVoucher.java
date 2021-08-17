package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import uk.co.jemos.podam.common.PodamExclude;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "phieunhap")
@ToString
public class ImportVoucher extends AuditableEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAPN", nullable = false)
    private Long id;

    @NotBlank(message = "Content is required")
    @Column(name = "NGAYLAP")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    @OneToMany(mappedBy = "importVoucher", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @PodamExclude
    private List<ImportVoucherDetail> importVoucherDetails;
}
