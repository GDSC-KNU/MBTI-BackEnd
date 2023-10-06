package com.gdsc.mbti.service;

import com.gdsc.mbti.config.JWTUtils;
import com.gdsc.mbti.dto.IdTokenRequestDto;
import com.gdsc.mbti.entity.Member;
import com.gdsc.mbti.repository.MemberRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JWTUtils jwtUtils;
    private final GoogleIdTokenVerifier verifier;

    public MemberService(@Value("${app.googleClientId}") String clientId, MemberRepository memberRepository,
                          JWTUtils jwtUtils) {
        this.memberRepository = memberRepository;
        this.jwtUtils = jwtUtils;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public String loginOAuthGoogle(IdTokenRequestDto requestBody) {
        Member member = verifyIDToken(requestBody.getIdToken());
        if (member == null) {
            throw new IllegalArgumentException();
        }
        member = createOrUpdateUser(member);
        return jwtUtils.createToken(member, false);
    }

    @Transactional
    public Member createOrUpdateUser(Member member) {
        Member existingMember = memberRepository.findByEmail(member.getEmail()).orElse(null);
        if (existingMember == null) {
            member.setRole("ROLE_USER");
            memberRepository.save(member);
            return member;
        }
        existingMember.setName(member.getName());
        existingMember.setProfile(member.getProfile());
        memberRepository.save(existingMember);
        return existingMember;
    }

    private Member verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = verifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String name = (String) payload.get("family_name") + (String) payload.get("given_name");
            String email = payload.getEmail();
            String profile = (String) payload.get("picture");

            return new Member(email, name, profile);
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
    public Member getGoogleLoginMember() {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) SecurityContextHolder.getContext();
        String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();

        return memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("회원의 정보가 일치하지 않습니다"));
    }
}
