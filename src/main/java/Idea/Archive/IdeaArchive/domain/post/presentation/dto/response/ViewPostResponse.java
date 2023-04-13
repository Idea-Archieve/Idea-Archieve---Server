package Idea.Archive.IdeaArchive.domain.post.presentation.dto.response;

import Idea.Archive.IdeaArchive.domain.member.presentation.dto.ViewMemberResponse;
import Idea.Archive.IdeaArchive.domain.post.category.Category;
import Idea.Archive.IdeaArchive.domain.post.entity.Heart;
import Idea.Archive.IdeaArchive.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ViewPostResponse {

    private Long id;
    private String title;
    private String content;
    private List<Category> category;
    private Integer heartCount;
    private Integer commentCount;
    private ViewMemberResponse member;

    public static ViewPostResponse convertToPost(Post post) {
        return ViewPostResponse.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .heartCount(post.getHeartCount())
                .commentCount(post.getCommentCount())
                .member(ViewMemberResponse.convertToMember(post.getMember()))
                .build();
    }
    public static List<ViewPostResponse> convertToPostList(List<Post> posts) {
        Stream<Post> stream = posts.stream();
        return stream.map(ViewPostResponse::convertToPost).collect(Collectors.toList());
    }
    public static ViewPostResponse convertToHeart(Heart heart) {
        return ViewPostResponse.builder()
                .id(heart.getPost().getPostId())
                .title(heart.getPost().getTitle())
                .content(heart.getPost().getContent())
                .category(heart.getPost().getCategory())
                .heartCount(heart.getPost().getHeartCount())
                .commentCount(heart.getPost().getCommentCount())
                .member(ViewMemberResponse.convertToMember(heart.getPost().getMember()))
                .build();
    }

    public static List<ViewPostResponse> convertToHeartList(List<Heart> hearts) {
        Stream<Heart> stream = hearts.stream();
        return stream.map(ViewPostResponse::convertToHeart).collect(Collectors.toList());
    }

}
