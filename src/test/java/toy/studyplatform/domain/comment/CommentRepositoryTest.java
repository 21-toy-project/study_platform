package toy.studyplatform.domain.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.comment.repository.CommentRepository;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.PostRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class CommentRepositoryTest {
    @Autowired private CommentRepository commentRepository;

    @Autowired private PostRepository postRepository;

    private Post post;

    @BeforeEach
    public void init() {
        String postTitle = "post-test-title-1";
        String postContent = "post-test-content-1";
        Long postWriterId = 0L;
        post = Post.builder().title(postTitle).content(postContent).writerId(postWriterId).build();
        postRepository.save(post);
    }

    @Test
    @DisplayName("comment 저장 성공 테스트")
    public void comment_저장_성공() {
        String commentContent = "comment 저장 성공 테스트 내용";
        Long commentWriterId = 1L;
        boolean isAnonymous = true;
        Comment comment =
                Comment.builder()
                        .writerId(commentWriterId)
                        .post(post)
                        .isAnonymous(isAnonymous)
                        .content(commentContent)
                        .build();

        Comment savedComment = commentRepository.save(comment);

        assertEquals(savedComment.getContent(), commentContent);
        assertEquals(savedComment.getPost(), post);
        assertEquals(savedComment.isAnonymous(), isAnonymous);
        assertEquals(savedComment.getWriterId(), commentWriterId);
    }

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
        commentRepository.deleteAll();
    }
}
