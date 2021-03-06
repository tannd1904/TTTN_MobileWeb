package com.tannd.commercemanager.dto;

import com.tannd.commercemanager.model.Category;
import com.tannd.commercemanager.model.ImportVoucherDetail;
import com.tannd.commercemanager.model.Review;
import com.tannd.commercemanager.model.Wishlist;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.swing.plaf.multi.MultiLabelUI;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ProductDTO extends AbstractDTO {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer status;

    private String image;

    private String description;

    private String type;

    private Double price;

    private Double promotion;

    private String madeIn;

    private Long categoryId;

    private List<ImportVoucherDetailDTO> importVoucherDetails;

    private List<WishlistDTO> wishlists;

    private List<ReviewDTO> reviews;

    private List<PromotionDTO> promotions;

    private List<PropertyDTO> properties;

}
