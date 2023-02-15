package Idea.Archieve.IdeaArchieve.domain.auth.service;


import Idea.Archieve.IdeaArchieve.domain.auth.entity.RefreshToken;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.request.MemberLoginRequest;
import Idea.Archieve.IdeaArchieve.domain.auth.presentation.dto.response.MemberLoginResponse;
import Idea.Archieve.IdeaArchieve.domain.auth.repository.RefreshTokenRepository;
import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MemberNotFoundException;
import Idea.Archieve.IdeaArchieve.domain.member.exception.MisMatchPasswordException;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.global.security.jwt.TokenProvider;
import Idea.Archieve.IdeaArchieve.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;


    public MemberLoginResponse execute(MemberLoginRequest memberLoginRequest){
        Member member = memberRepository.findByEmail(memberLoginRequest.getEmail())
                .orElseThrow(()->new MemberNotFoundException("존재하지 않은 회원입니다."));

        if(!passwordEncoder.matches(memberLoginRequest.getPassword(), member.getPassword())){
            throw new MisMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = tokenProvider.generatedAccessToken(memberLoginRequest.getEmail());
        String refreshToken = tokenProvider.generatedRefreshToken(memberLoginRequest.getEmail());
        RefreshToken entityRedis = new RefreshToken(memberLoginRequest.getEmail(), refreshToken,tokenProvider.getREFRESH_TOKEN_EXPIRE_TIME());

        refreshTokenRepository.save(entityRedis);

        return MemberLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiredAt(tokenProvider.getExpiredAtToken(accessToken,jwtProperties.getAccessSecret()))
                .build();
    }


}