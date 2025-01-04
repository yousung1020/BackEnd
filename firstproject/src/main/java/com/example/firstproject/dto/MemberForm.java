package com.example.firstproject.dto;
import com.example.firstproject.entity.Member;

public class MemberForm {
    // 필드 생성
    private String email;
    private String password;

    // 생성자 정의

    public MemberForm(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Member toEntity(){
        return new Member(null, email, password);
    }

}
