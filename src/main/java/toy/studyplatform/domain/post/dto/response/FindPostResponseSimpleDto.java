package toy.studyplatform.domain.post.dto.response;

import java.time.LocalDateTime;

import toy.studyplatform.domain.post.entity.Post;

public class FindPostResponseSimpleDto {
    private Long id;
    private String title;
    private Long writerId;
    private LocalDateTime createdDate;
    private Long countOfComments;

    public FindPostResponseSimpleDto(
            Long id, String title, Long writerId, LocalDateTime createdDate, Long countOfComments) {
        this.id = id;
        this.title = title;
        this.writerId = writerId;
        this.createdDate = createdDate;
        this.countOfComments = countOfComments;
    }

    public static FindPostResponseSimpleDto from(Post post, Long countOfComments) {
        final Long id = post.getId();
        final String title = post.getTitle();
        final Long writerId = post.getWriterId();
        final LocalDateTime createdDate = post.getCreatedDate();
        return new FindPostResponseSimpleDto(id, title, writerId, createdDate, countOfComments);
    }

    public String getTitle() {
        return title;
    }

    public Long getWriterId() {
        return writerId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Long getCountOfComments() {
        return countOfComments;
    }

    public FindPostResponseSimpleDto() {}
}
