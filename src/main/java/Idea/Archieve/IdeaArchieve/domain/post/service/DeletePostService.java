package Idea.Archieve.IdeaArchieve.domain.post.service;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotExistPostException;
import Idea.Archieve.IdeaArchieve.domain.post.exception.NotVerifyMember;
import Idea.Archieve.IdeaArchieve.domain.post.repository.PostRepository;
import Idea.Archieve.IdeaArchieve.global.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePostService {

    private final PostRepository postRepository;
    private final MemberUtil memberUtil;

    private void checkUser(Post post) {
        if(!memberUtil.currentMember().equals(post.getMember()))
            throw new NotVerifyMember("검증되지 않은 회원입니다.");
    }

    public void execute(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotExistPostException("존재하지 않는 게시글입니다."));
        checkUser(post);
        postRepository.deleteById(postId);
    }
}
