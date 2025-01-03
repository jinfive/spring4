package com.example.spring4.member.service;

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
//    public  MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
//        this.memberMapper = memberMapper;
//        this.passwordEncoder = passwordEncoder;
//    }
    //객체 생성시 생성자 호출할때 생성자의 파라메터 값으로
    //두 개의 주소를 찾아서 맴버 변수에 넣어주는 방식 생성자 주입방식



    public boolean login(MemberVO memberVO) {
        MemberVO member = memberMapper.selectMemberById(memberVO.getId());

        if (member != null) {
            // 데이터베이스에서 가져온 암호화된 비밀번호와 입력한 비밀번호 비교
            return passwordEncoder.matches(memberVO.getPw(), member.getPw());
        }
        return false; // 회원 정보가 없는 경우
    }

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
    //요청하나당 함수하나
    //처리내용
    // - 전처리하고 나서
    // - dao에 데이터를 넘기면서 db처리해줘 요청
    // - 후처리도 담당

    //db처리할 것은 무조건!! 서비스를 거쳐서 dao를 호출!!
    //front controller --> controller(login())
    //--> service(login()) ---> dao(selectMemberById())
}
