package Idea.Archive.IdeaArchive.domain.post.presentation;

import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.ModifyPostRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.request.WritePostRequest;
import Idea.Archive.IdeaArchive.domain.post.presentation.dto.response.*;
import Idea.Archive.IdeaArchive.domain.post.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final WritePostService writePostService;
    private final ViewPostService viewPostService;
    private final ViewPostByIdService viewPostByIdService;
    private final ModifyPostService modifyPostService;
    private final DeletePostService deletePostService;
    private final FilterPostService filterPostService;
    private final FilterPostByCategoryService filterPostByCategoryService;
    private final InsertHeartService insertHeartService;
    private final ViewHeartListService viewHeartListService;
    private final SharePostService sharePostService;
    private final ViewPostByHeartService viewPostByHeartService;

    @PostMapping("/write")
    public ResponseEntity<Void> writePost(@RequestBody @Valid WritePostRequest writePostRequest) {
        writePostService.execute(writePostRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ViewPostResponse>> viewNotice() {
        List<ViewPostResponse> response = viewPostService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ViewPostByIdResponse> viewPostById(@PathVariable Long postId) {
        ViewPostByIdResponse response = viewPostByIdService.execute(postId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody @Valid ModifyPostRequest modifyPostRequest) {
        modifyPostService.execute(postId, modifyPostRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        deletePostService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ViewByCategoryResponse>> SearchPost(@RequestParam String searchKeyword, @RequestParam String category){
        List<ViewByCategoryResponse> response =  filterPostService.execute(searchKeyword,category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ViewByCategoryResponse>> ViewPostByCategory(@RequestParam String category) {
        List<ViewByCategoryResponse> response = filterPostByCategoryService.execute(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{postId}/heart")
    public ResponseEntity<Void> insertHeart(@PathVariable("postId") Long postId) {
        insertHeartService.execute(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/heart")
    public ResponseEntity<List<ViewByHeartListResponse>> viewHeartList() {
        List<ViewByHeartListResponse> heartList = viewHeartListService.execute();
        return ResponseEntity.ok(heartList);
    }

    @GetMapping("/share/{postId}")
    public ResponseEntity<SharePostResponse> sharePost(@PathVariable Long postId) {
        SharePostResponse postUrl = sharePostService.execute(postId);
        return ResponseEntity.ok(postUrl);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ViewPostResponse>> viewPostByHeart(){
        List<ViewPostResponse> posts = viewPostByHeartService.execute();
        return ResponseEntity.ok(posts);
    }
}
