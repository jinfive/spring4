package com.example.spring4.product.dao;

import com.example.spring4.product.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO>  findAll();
    ProductVO findById(long id);
}
