package toy.studyplatform.domain.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
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
import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;
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
        String commentContent = "comment 저장 성공 테스트 내용";
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
        List<FindPostResponseSimpleDto> actualFindResponseSimpleDtoList =
                postRepository.findAllWithComments();
        FindPostResponseSimpleDto expectedFindResponseSimpleDto1 =
                FindPostResponseSimpleDto.from(post1, 1L);
        FindPostResponseSimpleDto expectedFindResponseSimpleDto2 =
                FindPostResponseSimpleDto.from(post2, 0L);

        // then
        assertEquals(actualFindResponseSimpleDtoList.size(), 2);

        assertEquals(
                actualFindResponseSimpleDtoList.get(0).getTitle(),
                expectedFindResponseSimpleDto1.getTitle());
        assertEquals(
                actualFindResponseSimpleDtoList.get(0).getWriterId(),
                expectedFindResponseSimpleDto1.getWriterId());
        assertEquals(
                actualFindResponseSimpleDtoList.get(0).getCreatedDate(),
                expectedFindResponseSimpleDto1.getCreatedDate());
        assertEquals(
                actualFindResponseSimpleDtoList.get(0).getCountOfComments(),
                expectedFindResponseSimpleDto1.getCountOfComments());

        assertEquals(
                actualFindResponseSimpleDtoList.get(1).getTitle(),
                expectedFindResponseSimpleDto2.getTitle());
        assertEquals(
                actualFindResponseSimpleDtoList.get(1).getWriterId(),
                expectedFindResponseSimpleDto2.getWriterId());
        assertEquals(
                actualFindResponseSimpleDtoList.get(1).getCreatedDate(),
                expectedFindResponseSimpleDto2.getCreatedDate());
        assertEquals(
                actualFindResponseSimpleDtoList.get(1).getCountOfComments(),
                expectedFindResponseSimpleDto2.getCountOfComments());
    }

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
    }
}
