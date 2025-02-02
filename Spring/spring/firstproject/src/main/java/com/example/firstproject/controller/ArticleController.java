package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller

public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 repository 객체 주입(DI: Dependency Injection, 의존성 주입)
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm () {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) { // ArticleForm 객체는 form의 인수 타입, 곧 dto 객체와 같음
        log.info(form.toString());
//        System.out.println(form.toString());
        // 엔티티: jpa(java persistence api)의 핵심 도구로써, 자바 객체를 db가 이해할 수 있게 만드는 것
        // 1. DTO를 엔티티로 변환하기
        Article article = form.toEntity();
        log.info(article.toString());
//        System.out.println(article.toString());
        // 2. repository로 엔티티를 db에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
//        System.out.println(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    // 데이터를 조회해 출력하기
    // 1. id를 조회해 db에서 데이터 가져오기
    // 2. 가져온 데이터를 모델에 등록하기
    // 3. 조회한 데이터를 사용자에게 보여주기 위한 뷰 페이지를 만들고 반환하기
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){  // 매개변수로 id 받아오기, @PathVariable 어노테이션은 url 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져옴
        log.info("id = " + id);
        // 1. id를 조회해 db에서 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 설정하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }

    // 데이터 수정하기
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정하기
        return "articles/edit";
    }
    // 데이터 수정 및 db에 저장
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();
        log.info((articleEntity.toString()));
        // 2-1. db에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            Article saved = articleRepository.save(articleEntity);
        }

        // 3. 수정 결과 페이지로 리다이렉트 하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    // 데이터 삭제하기
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!!");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!!!"); // (넘겨주려는 키 문자열, 넘겨 주려는 값 객체)
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }
}