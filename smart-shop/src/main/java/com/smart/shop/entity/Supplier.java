package com.smart.shop.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class Supplier implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String contact;
    private String phone;
    private String address;
}
