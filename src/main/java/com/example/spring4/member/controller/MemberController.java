package com.example.spring4.member.controller;

import com.example.spring4.member.service.MemberService;
import com.example.spring4.member.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //싱글톤객체생성 + 아래에 나온 주소와 함수를 스프링에 등록
@RequestMapping("member") //contextpath/member
@RequiredArgsConstructor //@Autowired
//멤버변수 @Autowired로 주입해줄 것을 모두 찾아서 넣어줌.
//@Slf4j //기본으로 지정된 클래스를 써줘(log-back 사용)
@Log4j2 //Slf4j표준에 따라서 만든 클래스인 Log4j를 써줘!
public class MemberController {

    //싱글톤은 프로젝트 시작할 때 만들어짐.@Service마찬가지
    //램에 어디에 MemberService싱글톤이 만들어졌는지 찾아야함.
    //@Autowired //램에 이미 만들어진 싱글톤 객체의 주소를 찾아서
    //아래에 있는 변수에 넣어줌.
    //찾을 때는 클래스로 만든 객체가 어디있는지 찾음.
    //클래스 타입으로 만든 객체의 주소를 찾아서
    //아래 변수에 넣어줌.
    final MemberService memberService; //100
    //    AService a;
//    BService b;
    @GetMapping("create") //contextpath/member/create
    public String create() {
        log.info(">>>>>>> 화면 요청 로그기록 >>>>>>>>>");
        System.out.println("create 화면 요청>>>>>>>>>>>>>>>> ");
        return "member/create";
    }

    @PostMapping("create2")
    public String create2(MemberVO memberVO) {
        //스프링에게 클라이언트가 입력한 정보를 받아서 vo객체생성 후
        //다 넣어줘. 제어를 프로그래머가 아니고 스프링이 다 해준다. (제어의 역전, IoC)
        //vo에 set메서드를 불러서 넣어주기 때문에 set메서드가 있어야함.
        System.out.println("memberVO =>>>>>>>>>> " + memberVO);
        memberService.create2(memberVO);
        return "member/member";
    }

    @GetMapping("member") //contextpath/member/member
    public String member() {
        System.out.println("member 화면 요청>>>>>>>>>>>>>>>> ");
        return "member/member";
    }

    @PostMapping("login")
    public String login(MemberVO memberVO,
                        HttpSession session,
                        Model model) {
        //컨트롤러의 함수를 호출할 때 스프링에 필요한 객체를 달라고 함.
        System.out.println("memberVO>>>>>>>>>>>>> " + memberVO);
        //서비스에 vo 받은 것 주면서 전처리해줘 요청!!!
        boolean result = memberService.login(memberVO);//boolean
        if (result) {
            //로그인되었다라는 정보를 서버에 남겨서
            //로그인 되었는지에 따라 화면 다르게 구성하고 싶은 경우
            //세션, id설정
            session.setAttribute("id", memberVO.getId());
        }else{
            model.addAttribute("result", "로그인실패!!재입력해주세요.!");
        }

        System.out.println("세션값 설정 확인 >> " + session.getAttribute("id"));

        //싱글톤 객체 찾아서 메서드 호출해야할 것 같음.
        //싱글톤 객체는 프로젝트 시작시
        // 어노테이션이나,
        // java, xml, properties를
        //다 스캔해서 싱글톤으로 만든다.
        return "member/member"; //로그인 페이지!
    }

    @GetMapping("logout") //컨텍스트패스/member/logout
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        System.out.println("로그아웃 성공 ============================== ");
        return "member/member";
    }

    @PostMapping("read")
    public String read(String id, Model model) { //<input name="id"
        System.out.println("member id >>>>>>>>>>>>> " + id);
        MemberVO memberVO = memberService.read(id);
        //view가 되는 read.html로 memberVO를 보내야하는 경우 Model model객체 필요
        model.addAttribute("memberVO", memberVO);
        return "member/read";
    }

    @GetMapping("delete")
    public String delete(String id, HttpSession session) {
        //컨트롤러의 메서드를 호출시 사용해야할 객체는
        //스프링에게 달라고 하면 됨. 메서드이름(요청객체, 요청객체2)
        System.out.println("member id >>>>>>>>>>>>> " + id);
        int result = memberService.delete(id);
        if (result > 0) { //성공하면, 세션 삭제후,
            session.removeAttribute("id");
            return "member/member";
        }else{
            return "error/error";
        }
    }

    @GetMapping("update") //수정 화면 요청
    public String update(String id, Model model) {
        System.out.println("member id >>>>>>>>>>>>> " + id);
        //id로 검색한 것을 수정화면에 보내자.
        MemberVO memberVO =  memberService.read(id);
        model.addAttribute("memberVO", memberVO);
        return "member/update";
    }

    @PostMapping("update2") //수정 처리 요청
    public String update2(MemberVO memberVO) {
        System.out.println("memberVO >>>>>>>>>>>>> " + memberVO);
        int result = memberService.update(memberVO);
        if (result > 0) {
            return "member/update2";
        }else{
            return "error/error";
        }
    }

    @GetMapping("checkId")
    @ResponseBody
    //뷰인 template가지 않고 단순한데이터나 json으로 보내겠다.
    public boolean checkId(@RequestParam String id) {
        //html이 아니라 체크한 결과를 단순하게 데이터를 보내는 경우
        //컨트롤러에서 바로 템플릿안쓰고 보낼 수 있음.
        System.out.println("member id >>>>>>>>>>>>> " + id);
        boolean result = memberService.checkId(id);
        //리턴은 http body로 단순한 데이터를 보내겠다라는 의미
        //return "아이디가 중복되지 않음. 사용가능 아이디임.";
        return result; //사용가능한 경우, 중복되지 않음.
    }




}