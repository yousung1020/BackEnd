package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

//    // 생성자 정의
//    public Member(Long id, String email, String password){
//        this.id = id;
//        this.email = email;
//        this.password = password;
//    }
//
//    @Override
//    public String toString(){
//        return "id: " + id +
//                ", email: " + email +
//                ", password: " + password;
//    }
}
