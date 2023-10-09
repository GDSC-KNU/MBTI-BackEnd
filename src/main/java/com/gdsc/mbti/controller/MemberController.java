//package com.gdsc.mbti.controller;
//
//import com.gdsc.mbti.dto.IdTokenRequestDto;
//import com.gdsc.mbti.dto.MemberDto;
//import com.gdsc.mbti.service.MemberService;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseCookie;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/v1/oauth")
//public class MemberController {
//
//    private final MemberService memberService;
//
//    @PostMapping("/login")
//    public ResponseEntity LoginWithGoogleOauth2(@RequestBody IdTokenRequestDto requestBody, HttpServletResponse response) {
//        String authToken = memberService.loginOAuthGoogle(requestBody);
//        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
//                .httpOnly(true)
//                .maxAge(7 * 24 * 3600)
//                .path("/")
//                .secure(false)
//                .build();
//        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/member/info")
//    public ResponseEntity<MemberDto> getMemberInfo(@RequestHeader("Authorization") String accessToken){
//        return ResponseEntity.ok(memberService.getMemberInfo(accessToken));
//    }
//}
