package com.example.spring4.product.controller;

import com.example.spring4.product.service.ProductService;
import com.example.spring4.product.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("product") //contextpath/product
@RequiredArgsConstructor //생성자를 통해서 주입해주세요.!
public class ProductController {

    private final ProductService productService;

    @GetMapping("product") //contextpath/product/product
    public String product(Model model) {
        System.out.println("product 화면 요청>>>>>>>>>>>>>>>> ");
        List<ProductVO> list = productService.findAll();
        //mybatis는 검색결과가 없으면 null이 아니고, 비어있는 리스트를 리턴
        //리스트있는지 체크할 때는 개수를 가지고 확인해야함.
        if (list.size() > 0) {
            System.out.println("=================================");
            System.out.println(list);
            model.addAttribute("list", list);
        }
        return "product/product";
    }

    @GetMapping("read/{productId}")
    public String read(Model model, @PathVariable long productId) {
        System.out.println("productId>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+productId);
        ProductVO productVO = productService.findById(productId);
        if (productVO != null) {
            model.addAttribute("productVO", productVO);
            return "product/read";
        } else {
            return "error/error";
        }
    }

}







