package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// dto: data transfer object (데이터 전송 객체), 컨트롤러에서 폼(form) 데이터를 받을 때 dto에 담아 받는 역할을 한다.
@AllArgsConstructor // 클래스 내부의 모든 필드를 매개변수로 하는 생성자가 자동으로 정의됨. 롬복의 어노테이션.
@ToString // toString() 메소드를 사용하는 것과 같다.
public class ArticleForm {
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    // 데이터를 잘 받았는지 확인할 toString() 메소드 추가
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity (){
        return new Article(null, title, content);
    }
}
