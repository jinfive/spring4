package com.example.spring4.board.service;


import com.example.spring4.board.dao.BoardMapper;
import com.example.spring4.board.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //아래 선언된 맴버변수에 싱글톤으로 생성된 주소를 찾아서 변수에 넣어줘
public class BoardService {
    //BoardMapper인터페이스에 따라 생성된 dao의 객체주소를 찾아서 변수에 넣어줘
    private final BoardMapper boardMapper;//다형성

   public int insertBoard(BoardVO boardVO) {
        return boardMapper.insertBoard(boardVO);
    }

    public BoardVO selectBoardByNo(int no) {
        return boardMapper.selectBoardByNo(no);
    }

    public List<BoardVO> selectBoardAll() {
        return boardMapper.selectBoardAll();
    }

    public int updateBoard(BoardVO boardVO) {
        return boardMapper.updateBoard(boardVO);
    }

    public int deleteBoard(int no) {
        return boardMapper.deleteBoard(no);
    }

    public List<BoardVO> getBoardByContent(String find) {
        return boardMapper.getBoardByContent(find);
    }
}
