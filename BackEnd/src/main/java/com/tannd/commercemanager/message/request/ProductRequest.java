package com.tannd.commercemanager.message.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductRequest {

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer status;
    private String type;
    private Double price;
    private String description;
    private Long categoryId;
    @JsonProperty("image")
    private String image;
}
