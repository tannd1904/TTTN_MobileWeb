package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.OrderDetail;
import com.tannd.commercemanager.model.Return;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ProductDetailDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String serial;

    private String color;

    private String cpu;

    private String ram;

    private String screen;

    private String memmory;

    private String camera;

    private String pin;

    private String os;

    private String note;

    private Double price;

    private Boolean status;

    private Long orderDetailId;

    private Long returnId;

    private Long importVoucherDetailId;
}
