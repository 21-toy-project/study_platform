package toy.studyplatform.domain.post.dto.response;

import java.time.LocalDateTime;

import toy.studyplatform.domain.post.entity.Post;

public class SavePostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public SavePostResponseDto() {}

    public SavePostResponseDto(
            Long id,
            String title,
            String content,
            Long writerId,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static SavePostResponseDto from(Post post) {
        final Long id = post.getId();
        final String title = post.getTitle();
        final String content = post.getContent();
        final Long writerId = post.getWriterId();
        final LocalDateTime createdDate = post.getCreatedDate();
        final LocalDateTime modifiedDate = post.getModifiedDate();
        return new SavePostResponseDto(id, title, content, writerId, createdDate, modifiedDate);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    @Override
    public String toString() {
        return "PostResponseDto{"
                + "id="
                + id
                + ", title='"
                + title
                + '\''
                + ", content='"
                + content
                + '\''
                + ", writerId="
                + writerId
                + ", createdDate="
                + createdDate
                + ", modifiedDate="
                + modifiedDate
                + '}';
    }
}
