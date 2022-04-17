package toy.study_platform.domain.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import toy.study_platform.domain.post.entity.Post;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {
    @Test
    @DisplayName("빌더 패턴으로 생성 잘 되는지 확인")
    void post_빌더패턴_생성자() {
        Long id = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;

        Post post = new Post
                .Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();

        assertEquals(post.getTitle(), title);
        assertEquals(post.getContent(), content);
        assertEquals(post.getWriterId(), writerId);
    }
}
