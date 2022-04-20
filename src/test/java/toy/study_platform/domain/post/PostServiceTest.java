package toy.study_platform.domain.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import toy.study_platform.domain.post.dto.PostSaveRequestDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;
import toy.study_platform.domain.post.service.PostService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    @DisplayName("새 post 저장 서비스 확인")
    public void post_저장_서비스() throws Exception {
        // given
        Long postId = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        PostSaveRequestDto postSaveRequestDto = new PostSaveRequestDto(title, content);
        Post post = postSaveRequestDto.toEntity(writerId);
        // test를 위해 generated value를 임의로 지정한다
        ReflectionTestUtils.setField(post, "id", postId);

        // mocking
        given(postRepository.save(any()))
                .willReturn(post);
        given(postRepository.findById(postId))
                .willReturn(Optional.ofNullable(post));

        // when
        Post savedPost = postService.save(postSaveRequestDto, writerId);

        // then
        Post findPost = postRepository.findById(savedPost.getId()).get();
        assertEquals(post.getId(), findPost.getId());
        assertEquals(post.getTitle(), findPost.getTitle());
        assertEquals(post.getContent(), findPost.getContent());
        assertEquals(post.getWriterId(), findPost.getWriterId());
    }
}
