package com.example.spring4.board.dao;

import com.example.spring4.board.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insertBoard(BoardVO boardVO);
    BoardVO selectBoardByNo(int no);
    List<BoardVO> selectBoardAll();
    int updateBoard(BoardVO boardVO);
    int deleteBoard(int no);
    List<BoardVO> getBoardByContent(String find);
}

