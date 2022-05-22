package toy.studyplatform.domain.post;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import toy.studyplatform.domain.post.dto.SavePostRequestDto;
import toy.studyplatform.domain.post.dto.SavePostResponseDto;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.service.PostCreator;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class PostRestControllerMockMvcTest {
    @Autowired private MockMvc mockMvc;

    @MockBean private PostCreator postCreator;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("post 생성 요청 성공 테스트")
    public void post_생성_요청_처리_성공() throws Exception {
        // given
        Long postId = 0L;
        String title = "test-title-1";
        String content = "test-content-1";
        Long writerId = 0L;
        SavePostRequestDto savePostRequestDto = SavePostRequestDto.of(title, content);

        Post post = Post.builder().title(title).content(content).writerId(writerId).build();
        ReflectionTestUtils.setField(post, "id", postId);

        SavePostResponseDto savePostResponseDto = SavePostResponseDto.from(post);

        given(postCreator.save(any(), any())).willReturn(savePostResponseDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/posts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(savePostRequestDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }
}
