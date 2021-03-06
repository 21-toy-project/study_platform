package toy.studyplatform.domain.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import toy.studyplatform.common.entity.BaseTimeEntity;

@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @NotNull
    @Column(length = 300)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    private Long writerId;

    protected Post() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private String content;
        private Long writerId;

        private Builder() {}

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder writerId(Long writerId) {
            this.writerId = writerId;
            return this;
        }

        public Post build() {
            Post post = new Post();
            if (title.isEmpty() || content.isEmpty() || writerId == null) {
                return null;
            }
            post.title = title;
            post.content = content;
            post.writerId = writerId;
            return post;
        }
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
}
