//package com.gdsc.mbti.entity;
//
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@NoArgsConstructor
//public class Member extends BaseTimeEntity{
//
//    // TODO: mbti 필드, post/reply 필드 추가
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String email;
//
//    private String name;
//
//    private String profile;
//
//    private String role;
//
//    @Builder
//    public Member(String email,String name, String profile) {
//        this.email = email;
//        this.name = name;
//        this.profile = profile;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setProfile(String profile) {
//        this.profile = profile;
//    }
//}
