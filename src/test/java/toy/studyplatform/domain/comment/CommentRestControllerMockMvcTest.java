package toy.studyplatform.domain.comment;

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

import toy.studyplatform.domain.comment.dto.CommentResDto;
import toy.studyplatform.domain.comment.dto.SaveCommentRequestDto;
import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.comment.service.CommentCreator;
import toy.studyplatform.domain.post.entity.Post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class CommentRestControllerMockMvcTest {
    @Autowired private MockMvc mockMvc;
    @MockBean private CommentCreator commentCreator;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("comment 생성 요청 성공 테스트")
    public void comment_생성_요청_처리_성공() throws Exception {
        // given - post
        Long postId = 0L;
        String postTitle = "post-test-title-1";
        String postContent = "post-test-content-1";
        Long postWriterId = 0L;
        Post post = Post.builder().title(postTitle).content(postContent).writerId(postWriterId).build();
        ReflectionTestUtils.setField(post, "id", postId);

        // given - comment
        Long commentId = 0L;
        String commentContent = "comment 저장 성공 테스트 내용";
        Long commentWriterId = 1L;
        boolean isAnonymous = true;
        SaveCommentRequestDto saveCommentRequestDto =
                SaveCommentRequestDto.of(commentContent, postId, isAnonymous);

        Comment comment = saveCommentRequestDto.toEntity(commentWriterId, post);
        ReflectionTestUtils.setField(comment, "id", commentId);

        CommentResDto commentResDto = CommentResDto.from(comment);

        given(commentCreator.save(any(), any())).willReturn(commentResDto);

        mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/comments")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(saveCommentRequestDto))
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(comment.getId()))
                .andExpect(jsonPath("$.content").value(comment.getContent()))
                .andExpect(jsonPath("$.postId").value(comment.getPost().getId()));
    }
}
