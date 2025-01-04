// 코드 로직 순서도
// 1. url 매핑 후 뷰 탬플릿 반환(GerMapping)
// 2. submit에 의한 수신 컨트롤러 구현 (PostMapping)
// 3. 컨트롤러 메소드 생성

package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String newPage(){
        return "member/mem";
    }

    @PostMapping("/join")
    public String memberData(MemberForm form){
        // 1. dto 객체를 엔티티로
        Member member = form.toEntity();

        // 2. repository로 저장
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
