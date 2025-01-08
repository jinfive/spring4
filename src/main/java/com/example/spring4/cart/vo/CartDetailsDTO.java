package com.example.spring4.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailsDTO {


    private long no;
    private String memberId;
    private String memberName;
    private String memberTel;
    private long productId;
    private String productName;
    private int productPrice;
    private int count;
}


