package toy.study_platform.domain.post.entity;


import toy.study_platform.common.entity.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length=300, nullable=false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private Long writerId;

    protected Post() {
    }

    public static class Builder {
        private String title;
        private String content;
        private Long writerId;

        public Builder() {
        }

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
