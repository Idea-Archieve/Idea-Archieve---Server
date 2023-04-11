package Idea.Archive.IdeaArchive.domain.comment.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.comment.entity.Comment;
import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewCommentByPostResponse {

    private Long commentId;
    private String content;
    private String time;
    private ViewMemberResponse member;

    public static ViewCommentByPostResponse convertToComment(Comment comment) {
        return ViewCommentByPostResponse.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .time(comment.getTime())
                .member(ViewMemberResponse.convertToMember(comment.getMember()))
                .build();
    }

    public static List<ViewCommentByPostResponse> convertToCommentList(List<Comment> commentList) {
        Stream<Comment> stream = commentList.stream();
        return stream.map(ViewCommentByPostResponse::convertToComment)
                .collect(Collectors.toList());
    }

}
