package Idea.Archieve.IdeaArchieve.domain.member.service;

import Idea.Archieve.IdeaArchieve.domain.member.entity.Member;
import Idea.Archieve.IdeaArchieve.domain.member.exception.AlreadyExistNicknameException;
import Idea.Archieve.IdeaArchieve.domain.member.presentation.dto.request.ChangeNameRequest;
import Idea.Archieve.IdeaArchieve.domain.member.repository.MemberRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangeNameService {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public void execute(ChangeNameRequest changeNameRequest){
        Member member = memberUtil.currentMember();
        if (memberRepository.existsByName(changeNameRequest.getName())){
            throw new AlreadyExistNicknameException("이미 존재하는 닉네임입니다");
        }
        member.updateName(changeNameRequest.getName());
    }
}