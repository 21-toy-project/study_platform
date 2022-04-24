package toy.study_platform.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import toy.study_platform.domain.post.dto.PostRequestDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.service.PostCreator;

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
    private PostCreator postService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("post 생성 요청 성공 테스트")
    public void post_생성_요청_처리_성공() throws Exception {
        // given
        Long postId = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        PostRequestDto postRequestDto = PostRequestDto.of(title, content);

        Post post = new Post.Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
        ReflectionTestUtils.setField(post, "id", postId);

        given(postService.save(any(), any())).willReturn(post);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(postRequestDto))
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                    .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()));

    }
}
