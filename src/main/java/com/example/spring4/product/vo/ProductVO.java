package com.example.spring4.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//toSTring,Setter,Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductVO {
    private long productId;
    private String name;
    private String content;
    private String company;
    private String img;
    private int price;

}
