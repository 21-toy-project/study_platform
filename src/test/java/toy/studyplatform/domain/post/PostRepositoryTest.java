package toy.studyplatform.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.PostRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PostRepositoryTest {
    @Autowired private PostRepository postRepository;

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

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
    }
}
