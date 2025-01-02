package com.example.spring4.board.controller;

import com.example.spring4.board.service.BoardService;
import com.example.spring4.board.vo.BoardVO;
import com.example.spring4.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("board") //contextpath/board
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("board") //contextpath/board/board
    public String board(Model model) {
        System.out.println("board 화면 요청>>>>>>>>>>>>>>>> ");
        //전채 목록
        List<BoardVO> list = boardService.selectBoardAll();
        System.out.println("리스트 사이즈>>>>>>>>>>>>>>>>>>>"+list.size());
        System.out.println("리스트>>>>>>>>>>>>>>>>>>>>>>>>>>"+list);
        //model은 templates파일까지 list 데이터 전달 (주소가 전달)
        model.addAttribute("list", list);
        return "board/board";
    }

    @GetMapping("create")
    public String create() {
        return "board/create";
    }

    @PostMapping("create2")
    public String create2(BoardVO boardVO , Model model) {
        boardService.insertBoard(boardVO);
        //db에 넣고 -> 삽입 성공 또는 게시판 리스트
        //list를 구해서 넘겨야 하는데 전달될 list가 없어서 빈화면이 나타남.

        System.out.println("게시글 작성 결과>>>>>>>>>>>>>>>>>>>>>>"+boardVO);
        model.addAttribute("board", boardVO);
        return "redirect:/board/board";
        //return "/board/board";
        //respose.sednRedirect("/board/board)를  브라우저에게 호출하도록 명령
        //get 요청
    }
    @GetMapping("read")
    public String read(int no ,Model model) {
        //검색해서 가지고 온 다음에
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO", boardVO);
        return "board/read";

    }
    @GetMapping("delete")
    public String delete(int no) {
        int result =boardService.deleteBoard(no);
        if(result == 1) {
            return "redirect:/board/board";
        }
        else {
            return "error/error";
        }


    }

    @GetMapping("update")
    public String update(int no,Model model) {
        BoardVO boardVO = boardService.selectBoardByNo(no);
        model.addAttribute("boardVO", boardVO);
        return "board/update";

    }

    @PostMapping("update2")
    public String update2(BoardVO boardVO ) {
        boardService.updateBoard(boardVO);
        return "board/update2";
    }


}
