// 코드 로직 순서도
// 1. url 매핑 후 뷰 탬플릿 반환(GerMapping)
// 2. submit에 의한 수신 컨트롤러 구현 (PostMapping)
// 3. 컨트롤러 메소드 생성

package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;
    public MemberController(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members/signup")
    public String newPage(){
        return "member/mem";
    }

    @PostMapping("/members/join")
    public String memberData(MemberForm form){
        // 1. dto 객체를 엔티티로
        Member member = form.toEntity();

        // 2. repository로 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    // 1. 레포지토리에서 id 값 가져오기
    // 2. 데이터를 모델에 등록하기
    // 3. 출력할 뷰 페이지 설정하기
    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        // id 값을 repository에서 조회 후 memberEntity에 할당
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 해당 값을 모델에 등록
        model.addAttribute("member1", memberEntity);
        // 뷰페이지에 등록
        return "member/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        // repository에서 데이터 가져오기(findAll() 함수)
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        // model에 등록
        model.addAttribute("memberList", memberEntityList);
        // 뷰 페이지 반환
        return "member/index";
    }

    // 정보 수정하기 - edit 페이지 반환
    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정 할 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 모델에 등록하기
        model.addAttribute("member", memberEntity);

        return "member/edit";
    }

    // 정보 수정하기 - 폼 데이터를 수정하기
    @PostMapping("/members/update")
    public String update(MemberForm form){
        // dto를 엔티티로 변환
        Member memberEntity = form.toEntity();

        // 기존의 데이터를 가져오기
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        // 데이터 갱신하기
        if (target != null){
            Member saved = memberRepository.save(memberEntity);
        }

        return "redirect:/members/" + memberEntity.getId();
    }
}

