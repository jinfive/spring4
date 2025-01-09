package com.example.spring4.cart.service;

import com.example.spring4.cart.dao.CartMapper;
import com.example.spring4.cart.vo.CartDetailsDTO;
import com.example.spring4.cart.vo.CartVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CartService {

    private final CartMapper cartMapper;

    //장바구니에 넣기
    public int createCart(CartVO cartVO){
        return cartMapper.createCart(cartVO);
    }

    //장바구니 로그인사람기준으로 검색 목록
    public List<CartDetailsDTO> findCartsByMemberId(String memberId){
        return cartMapper.findCartsByMemberId(memberId);
    }

    //장바구니 물건 하나 삭제
    public int deleteProduct(long no){
        return cartMapper.deleteProduct(no);
    } //cartId

    //장바구니 여러개 삭제
    public int deleteProducts(List<Long> deleteNoList){
        return cartMapper.deleteProducts(deleteNoList);
    }

    //장바구니 수량 수정
    public int updateCount(CartVO cartVO){
        return cartMapper.updateCount(cartVO);
    }

    CartVO findProductByMemberId(long ProductId,
                                 String memberId){
        return cartMapper.findProductByMemberId(ProductId, memberId);
    }
}