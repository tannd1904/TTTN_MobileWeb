package com.tannd.commercemanager.model.key;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PromotionId implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long product;

  private Long accessory;
}
