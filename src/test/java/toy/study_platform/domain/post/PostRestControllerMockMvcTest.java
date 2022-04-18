package toy.study_platform.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import toy.study_platform.domain.post.dto.PostSaveRequestDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;
import toy.study_platform.domain.post.service.PostService;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class PostRestControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("post 생성 요청 성공 테스트")
    public void 정상적인_post_생성_요청_처리() throws Exception {
        // given
        Long postId = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        PostSaveRequestDto postSaveRequestDto = new PostSaveRequestDto(title, content);

        Post post = new Post.Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
        ReflectionTestUtils.setField(post, "id", postId);

        given(postService.add(any(), any())).willReturn(post);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postSaveRequestDto))
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                    .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()));

    }
}
