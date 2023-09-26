package com.gdsc.mbti.controller;

import com.gdsc.mbti.dto.IdTokenRequestDto;
import com.gdsc.mbti.dto.MemberDto;
import com.gdsc.mbti.entity.Member;
import com.gdsc.mbti.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/v1/oauth")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity LoginWithGoogleOauth2(@RequestBody IdTokenRequestDto requestBody, HttpServletResponse response) {
        String authToken = memberService.loginOAuthGoogle(requestBody);
        final ResponseCookie cookie = ResponseCookie.from("AUTH-TOKEN", authToken)
                .httpOnly(true)
                .maxAge(7 * 24 * 3600)
                .path("/")
                .secure(false)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/member/info")
    public ResponseEntity getMemberInfo(@AuthenticationPrincipal Member currentMember) {
        // 멤버 이름에 Long.valueOf() 사용...
//        Member member = memberService.getMember(Long.valueOf(principal.getName()));
        return ResponseEntity.ok().body(MemberDto.convertToDto(currentMember));
    }
}
