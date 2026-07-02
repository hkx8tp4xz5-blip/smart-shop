package com.smart.shop.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String photoUrl;
    private Double price;
    private String descp;
    private String releaseDate;
    private Integer catId;
    private String categoryName;
}
