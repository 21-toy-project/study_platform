package toy.studyplatform.domain.post;

import java.util.List;

import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import toy.studyplatform.config.TestConfig;
import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.comment.repository.jpa.CommentRepository;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.jpa.PostRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
@Import(TestConfig.class)
public class PostRepositoryTest {
    @Autowired private PostRepository postRepository;
    @Autowired private CommentRepository commentRepository;

    @Test
    @DisplayName("post 저장 성공 테스트")
    public void post_저장_성공() {
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;

        Post post = Post.builder().title(title).content(content).writerId(writerId).build();

        Post savedPost = postRepository.save(post);

        assertEquals(savedPost.getTitle(), title);
        assertEquals(savedPost.getContent(), content);
        assertEquals(savedPost.getWriterId(), writerId);
    }

    @Test
    @DisplayName("전체 post 목록 조회 성공 테스트")
    public void post_목록_조회_성공() throws Exception {
        // given - posts
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        Post post1 = Post.builder().title(title).content(content).writerId(writerId).build();
        postRepository.save(post1);

        String title2 = "test-title-2";
        String content2 = "test-content-2";
        Long writerId2 = 0L;
        Post post2 = Post.builder().title(title2).content(content2).writerId(writerId2).build();
        postRepository.save(post2);

        // given - comments
        String commentContent = "comment 내용";
        Long commentWriterId = 1L;
        boolean isAnonymous = true;
        Comment comment =
                Comment.builder()
                        .writerId(commentWriterId)
                        .post(post1)
                        .isAnonymous(isAnonymous)
                        .content(commentContent)
                        .build();
        commentRepository.save(comment);

        // when
        Pageable pageable = PageRequest.of(0, 5);
        List<Tuple> actualPostList = postRepository.findAllWithComments(pageable);

        // then
        assertEquals(actualPostList.size(), 2);

        Post actualPost1 = actualPostList.get(1).get(0, Post.class);
        Long actualCountOfComments1 = actualPostList.get(1).get(1, Long.class);
        assertEquals(actualPost1.getTitle(), post1.getTitle());
        assertEquals(actualPost1.getWriterId(), post1.getWriterId());
        assertEquals(actualPost1.getCreatedDate(), post1.getCreatedDate());
        assertEquals(actualCountOfComments1, 1L);

        Post actualPost2 = actualPostList.get(0).get(0, Post.class);
        Long actualCountOfComments2 = actualPostList.get(0).get(1, Long.class);
        assertEquals(actualPost2.getTitle(), post2.getTitle());
        assertEquals(actualPost2.getWriterId(), post2.getWriterId());
        assertEquals(actualPost2.getCreatedDate(), post2.getCreatedDate());
        assertEquals(actualCountOfComments2, 0L);
    }

    @Test
    @DisplayName("전체 post 목록 조회 페이징 처리 성공 테스트")
    public void post_전체_목록_조회_페이징_처리_성공() throws Exception {
        // given - post
        for (int i = 0; i < 10; i++) {
            String title = "test-title-" + i;
            String content = "test-content-" + i;
            Long writerId = 0L;
            Post post = Post.builder().title(title).content(content).writerId(writerId).build();
            postRepository.save(post);
        }

        // when
        Pageable pageable = PageRequest.of(0, 5);
        List<Tuple> actualPostList = postRepository.findAllWithComments(pageable);

        // then
        assertEquals(actualPostList.size(), 5);
    }

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
    }
}
