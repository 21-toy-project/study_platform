package toy.studyplatform.domain.post;

import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

import toy.studyplatform.domain.post.dto.request.SavePostRequestDto;
import toy.studyplatform.domain.post.dto.response.SavePostResponseDto;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.jpa.PostRepository;
import toy.studyplatform.domain.post.service.PostCreator;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class PostCreatorTest {
    @Mock private PostRepository postRepository;

    @InjectMocks private PostCreator postCreator;

    @Test
    @DisplayName("새 post 저장 서비스 성공 테스트")
    public void post_저장_서비스_성공() throws Exception {
        // given
        Long postId = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        SavePostRequestDto savePostRequestDto = SavePostRequestDto.of(title, content);
        Post post = savePostRequestDto.toEntity(writerId);
        // test를 위해 generated value를 임의로 지정한다
        ReflectionTestUtils.setField(post, "id", postId);
        SavePostResponseDto expectedSavePostResponseDto = SavePostResponseDto.from(post);

        // mocking
        given(postRepository.save(any())).willReturn(post);
        given(postRepository.findById(postId)).willReturn(Optional.ofNullable(post));

        // when
        SavePostResponseDto actualSavePostResponseDto = postCreator.save(savePostRequestDto, writerId);

        // then
        Post findPost = postRepository.findById(actualSavePostResponseDto.getId()).get();
        assertEquals(post.getId(), findPost.getId());
        assertEquals(post.getTitle(), findPost.getTitle());
        assertEquals(post.getContent(), findPost.getContent());
        assertEquals(post.getWriterId(), findPost.getWriterId());
        assertEquals(expectedSavePostResponseDto.toString(), actualSavePostResponseDto.toString());
    }
}
