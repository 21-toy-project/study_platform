package toy.studyplatform.domain.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.post.entity.Post;

public class CommentTest {

    private Post post;

    @BeforeEach
    public void init() {
        String postTitle = "post-test-title-1";
        String postContent = "post-test-content-1";
        Long postWriterId = 0L;
        post = Post.builder().title(postTitle).content(postContent).writerId(postWriterId).build();
    }

    @Test
    public void comment_글쓴이_익명댓글_남길수없음_성공_테스트() {
        String commentContent = "comment 저장 성공 테스트 내용 2";
        Long commentWriterId = 0L;
        boolean isAnonymous = true;
        Comment comment =
                Comment.builder()
                        .writerId(commentWriterId)
                        .post(post)
                        .isAnonymous(isAnonymous)
                        .content(commentContent)
                        .build();

        assertEquals(comment.getContent(), commentContent);
        assertEquals(comment.getPost(), post);
        assertEquals(comment.isAnonymous(), false);
        assertEquals(comment.getWriterId(), commentWriterId);
    }
}
