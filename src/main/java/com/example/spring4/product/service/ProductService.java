package com.example.spring4.product.service;

import com.example.spring4.product.dao.ProductMapper;
import com.example.spring4.product.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService {
   private final ProductMapper productMapper;

    public List<ProductVO> findAll(){
        return productMapper.findAll();
    }
    public ProductVO findById(long id){
        return productMapper.findById(id);
    }
}
