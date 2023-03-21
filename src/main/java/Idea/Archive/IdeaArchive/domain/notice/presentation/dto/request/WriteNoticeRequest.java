package Idea.Archive.IdeaArchive.domain.notice.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class WriteNoticeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}