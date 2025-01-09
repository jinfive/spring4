package com.example.spring4.cart.dao;

import com.example.spring4.cart.vo.CartDetailsDTO;
import com.example.spring4.cart.vo.CartVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CartMapper {
    //장바구니에 넣기
    int createCart(CartVO cartVO);

    //장바구니 로그인사람기준으로 검색 목록
    List<CartDetailsDTO> findCartsByMemberId(String memberId);

    //장바구니 물건 하나 삭제
    int deleteProduct(long no);

    //장바구니 여러개 삭제
    int deleteProducts(List<Long> deleteNoList);

    //장바구니 수량 수정
    int updateCount(CartVO cartVO);

    //이미 장바구니에 추가된 물건이 있는지 확인
    //있으면, 기존에 5 --> +1 (6)갱신
    //없으면, 추가하면 됨.
    //이 사람이 이 물건을 장바구니에 이미 넣었는지 확인하기 위해
    //물건id, 사람id
    CartVO findProductByMemberId(@Param("productId") long ProductId,
                                 @Param("memberId")String memberId);
}
