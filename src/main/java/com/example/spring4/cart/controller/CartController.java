package com.example.spring4.cart.controller;

import com.example.spring4.cart.service.CartService;
import com.example.spring4.cart.vo.CartDetailsDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("cart") //contextpath/cart
@RequiredArgsConstructor
public class CartController {

private final CartService cartService;



    @GetMapping("cart") //contextpath/cart/cart
    public String cart(HttpSession session, Model model) {
        System.out.println("cart 화면 요청>>>>>>>>>>>>>>>> ");
        String loginId = session.getAttribute("id").toString();
        List<CartDetailsDTO> list = cartService.findCartsByMemberId(loginId);
        if (list.size() > 0) {
            model.addAttribute("carts", list);
        }

        return "cart/cart";
    }
}
