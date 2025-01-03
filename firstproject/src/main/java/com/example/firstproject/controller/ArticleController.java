package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        System.out.println(form.toString());
        // 엔티티: jpa(java persistence api)의 핵심 도구로써, 자바 객체를 db가 이해할 수 있게 만드는 것
        // 1. DTO를 엔티티로 변환하기
        Article article = form.toEntity();
        System.out.println(article.toString());
        // 2. repository로 엔티티를 db에 저장
        Article saved = articleRepository.save(article); // article 엔티티를 저장해 saved 객체에 반환
        System.out.println(saved.toString());

        return "";
    }
}
