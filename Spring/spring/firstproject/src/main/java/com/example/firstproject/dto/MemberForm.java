package com.example.firstproject.dto;
import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    // 필드 생성
    private String email;
    private String password;
    private Long id;

    // 생성자 정의

//    public MemberForm(String email, String password){
//        this.email = email;
//        this.password = password;
//    }

    public Member toEntity(){
        return new Member(id, email, password);
    }

}
