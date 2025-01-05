package com.example.spring4.member.service;

import com.example.spring4.member.dao.MemberMapper;
import com.example.spring4.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring4.member.dao.MemberMapper;
import com.example.spring4.member.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service //싱글톤객체 생성 + 비지니스 레이어(층)로 등록
@RequiredArgsConstructor  //@Autowired
//아래 선언된 멤버변수에 객체의 주소를 찾아서 다 넣어줌.
public class MemberService {

    private final MemberMapper memberMapper; //200(DI)
    private final PasswordEncoder passwordEncoder; //300(DI)

//    @Autowired
//    public MemberService( MemberMapper memberMapper,
//                          PasswordEncoder passwordEncode){
//        this.memberMapper = memberMapper;
//        this.passwordEncoder = passwordEncode;
//    } //객체생성시 생성자호출할 때 생성자의 파라메터 값으로
    //두 개의 주소를 찾아서 멤버변수에 넣어주는 방식
//생성자 주입 방식

    public boolean login(MemberVO memberVO) {
        //전처리하고
        //dao를 찾아서 --> ok
        //dao.메서드 호출
        MemberVO memberVO1 = memberMapper.selectMemberById(memberVO.getId());
        if (memberVO1 != null) {
            if (passwordEncoder.matches(memberVO.getPw(), memberVO1.getPw())) {
                return true; //로그인 성공
            } else {
                return false; //로그인 실패
            }
        }else{
            return false; //로그인 실패
        }
        //후처리
    }//login

    public int create2(MemberVO memberVO) {
        //mapper에게 주고 db처리해줘...
        String pw2 = passwordEncoder.encode(memberVO.getPw());
        memberVO.setPw(pw2);
        System.out.println("vo에 암호화된 pw >>>>>> " + memberVO.getPw());
        int result = memberMapper.insertMember(memberVO);
        return result;
    }

    public MemberVO read(String id) {
        return memberMapper.selectMemberById(id);
    }

    public int delete(String id) {
        return memberMapper.deleteMember(id);
    }

    public int update(MemberVO memberVO) {
        return memberMapper.updateMember(memberVO);
    }

    public boolean checkId(String id) {
        return memberMapper.selectMemberById(id) == null;
        //가입하려고 하는 id를 가지고 검색을 해서 null이 아니면
        //이 아이디로 이미 가입이 되어있다라는 얘기 --> 사용할 수 없는 아이디로 처리!
        //null이면 이 id로 가입한 사람이 없다라는 얘기 --> 사용할 수 있는 아이디로 처리!
    }
    //요청하나당 함수하나
    //처리내용
    // - 전처리하고 나서
    // - dao에 데이터를 넘기면서 db처리해줘 요청
    // - 후처리도 담당

    //db처리할 것은 무조건!! 서비스를 거쳐서 dao를 호출!!
    //front controller --> controller(login())
    //--> service(login()) ---> dao(selectMemberById())
}