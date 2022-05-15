package toy.studyplatform.domain.comment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.post.entity.Post;

public class SaveCommentRequestDto {
    @JsonProperty @NotBlank private String content;

    @JsonProperty @NotBlank private Long postId;

    @JsonProperty @NotNull private boolean anonymous;

    private SaveCommentRequestDto(String content, Long postId, boolean anonymous) {
        this.content = content;
        this.postId = postId;
        this.anonymous = anonymous;
    }

    public Comment toEntity(Long writerId, Post post) {
        return Comment.builder()
                .anonymous(anonymous)
                .content(content)
                .post(post)
                .writerId(writerId)
                .build();
    }

    public Long getPostId() {
        return postId;
    }
}
