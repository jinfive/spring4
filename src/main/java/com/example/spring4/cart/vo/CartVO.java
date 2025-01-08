package com.example.spring4.cart.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartVO {
    private long no;
    private long productId;
    private String memberId;
    private int count = 1;
}