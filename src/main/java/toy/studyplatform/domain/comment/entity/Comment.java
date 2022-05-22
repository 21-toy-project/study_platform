package toy.studyplatform.domain.comment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import toy.studyplatform.common.entity.BaseTimeEntity;
import toy.studyplatform.domain.post.entity.Post;

@Entity
public class Comment extends BaseTimeEntity {
    @Id @GeneratedValue private Long id;

    @Column(length = 500, nullable = false)
    private String content;

    private Long writerId;

    private boolean isAnonymous;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "post_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_COMMENT_POST_ID"))
    private Post post;

    protected Comment() {}

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String content;
        private Long writerId;
        private boolean isAnonymous;
        private Post post;

        private Builder() {}

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder writerId(Long writerId) {
            this.writerId = writerId;
            return this;
        }

        public Builder isAnonymous(boolean isAnonymous) {
            if (post.getWriterId().equals(writerId)) {
                this.isAnonymous = false;
                return this;
            }
            this.isAnonymous = isAnonymous;
            return this;
        }

        public Builder post(Post post) {
            this.post = post;
            return this;
        }

        public Comment build() {
            Comment comment = new Comment();
            if (content.isEmpty() || writerId == null || post == null) {
                return null;
            }
            comment.content = content;
            comment.writerId = writerId;
            comment.isAnonymous = isAnonymous;
            comment.post = post;
            return comment;
        }
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
        return isAnonymous;
    }

    public Post getPost() {
        return post;
    }
}
