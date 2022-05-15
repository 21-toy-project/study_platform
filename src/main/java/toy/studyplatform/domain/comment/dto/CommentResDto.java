package toy.studyplatform.domain.comment.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import toy.studyplatform.domain.comment.entity.Comment;

public class CommentResDto {
    private Long id;
    private String content;
    private Long writerId;
    private boolean anonymous;
    private Long postId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public CommentResDto(
            Long id,
            String content,
            Long writerId,
            boolean anonymous,
            Long postId,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;
        this.anonymous = anonymous;
        this.postId = postId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static CommentResDto from(Comment comment) {
        final Long id = comment.getId();
        final String content = comment.getContent();
        final Long writerId = comment.getWriterId();
        final boolean anonymous = comment.isAnonymous();
        final Long postId = comment.getPost().getId();
        final LocalDateTime createdDate = comment.getCreatedDate();
        final LocalDateTime modifiedDate = comment.getModifiedDate();
        return new CommentResDto(id, content, writerId, anonymous, postId, createdDate, modifiedDate);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public Long getPostId() {
        return postId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentResDto that = (CommentResDto) o;
        return anonymous == that.anonymous
                && Objects.equals(id, that.id)
                && Objects.equals(content, that.content)
                && Objects.equals(writerId, that.writerId)
                && Objects.equals(postId, that.postId)
                && Objects.equals(createdDate, that.createdDate)
                && Objects.equals(modifiedDate, that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, writerId, anonymous, postId, createdDate, modifiedDate);
    }
}
