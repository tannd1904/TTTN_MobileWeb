package com.tannd.commercemanager.model;

import com.tannd.commercemanager.model.audit.AuditableEntity;
import com.tannd.commercemanager.model.key.PromotionId;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(PromotionId.class)
@Table(name= "uudai")
@ToString
public class Promotion extends AuditableEntity {

  private static final long serialVersionUID = 1L;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinColumn(name = "MADONGSP")
  private Product product;

  @Id
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "MAPK")
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private Accessory accessory;

}
