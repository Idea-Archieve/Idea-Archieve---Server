package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response.ViewCommentByPostResponse;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewPostByIdResponse {

    private Long id;
    private String title;
    private String content;
    private List<String> category;
    private ViewMemberResponse member;
    private List<ViewCommentByPostResponse> comment;
    private Integer heartCount;
    private Integer commentCount;


}