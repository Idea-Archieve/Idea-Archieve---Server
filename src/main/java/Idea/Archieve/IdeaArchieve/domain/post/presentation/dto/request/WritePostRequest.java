package Idea.Archieve.IdeaArchieve.domain.post.presentation.dto.request;

import Idea.Archieve.IdeaArchieve.domain.member.Entity.Member;
import Idea.Archieve.IdeaArchieve.domain.post.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WritePostRequest {

    @NotBlank(message = "제목은 공백을 허용하지 않습니다.")
    private String title;

    @NotBlank(message = "내용은 공백을 허용하지 않습니다.")
    private String content;

    @NotBlank(message = "카테고리은 공백을 허용하지 않습니다.")
    private String category;

    public Post toEntity(Member member) {
        return Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .member(member)
                .build();
    }
}