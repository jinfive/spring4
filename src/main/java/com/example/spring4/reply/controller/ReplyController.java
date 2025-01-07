package com.example.spring4.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("cart") //contextpath/cart
public class CartController {

    @GetMapping("cart") //contextpath/cart/cart
    public String cart() {
        System.out.println("cart 화면 요청>>>>>>>>>>>>>>>> ");
        return "cart/cart";
    }
}
