package toy.studyplatform.domain.comment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.post.entity.Post;

public class SaveCommentRequestDto {
    @JsonProperty @NotBlank private String content;

    @JsonProperty @NotNull private Long postId;

    @JsonProperty @NotNull private boolean isAnonymous;

    public static SaveCommentRequestDto of(String content, Long postId, boolean isAnonymous) {
        return new SaveCommentRequestDto(content, postId, isAnonymous);
    }

    private SaveCommentRequestDto() {}

    private SaveCommentRequestDto(String content, Long postId, boolean isAnonymous) {
        this.content = content;
        this.postId = postId;
        this.isAnonymous = isAnonymous;
    }

    public Comment toEntity(Long writerId, Post post) {
        return Comment.builder()
                .post(post)
                .isAnonymous(isAnonymous)
                .content(content)
                .writerId(writerId)
                .build();
    }

    public Long getPostId() {
        return postId;
    }
}
