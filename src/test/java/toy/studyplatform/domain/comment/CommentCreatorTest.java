package toy.studyplatform.domain.comment;

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

import toy.studyplatform.domain.comment.dto.CommentResDto;
import toy.studyplatform.domain.comment.dto.SaveCommentRequestDto;
import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.comment.repository.CommentRepository;
import toy.studyplatform.domain.comment.service.CommentCreator;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class CommentCreatorTest {
    @Mock private PostRepository postRepository;

    @Mock private CommentRepository commentRepository;

    @InjectMocks private CommentCreator commentCreator;

    @Test
    @DisplayName("comment 저장 서비스 성공 테스트")
    public void comment_저장_서비스_성공() throws Exception {
        // given - post
        Long postId = 0L;
        String postTitle = "post-test-title-1";
        String postContent = "post-test-content-1";
        Long postWriterId = 0L;
        Post post = Post.builder().title(postTitle).content(postContent).writerId(postWriterId).build();

        // mocking - post
        given(postRepository.getById(postId)).willReturn(post);

        // given - comment
        Long commentId = 0L;
        String commentContent = "comment 저장 성공 테스트 내용";
        Long commentWriterId = 1L;
        boolean anonymous = true;
        SaveCommentRequestDto saveCommentRequestDto =
                SaveCommentRequestDto.of(commentContent, postId, anonymous);
        Comment comment = saveCommentRequestDto.toEntity(commentWriterId, post);
        ReflectionTestUtils.setField(comment, "id", commentId);
        CommentResDto expectedCommentResDto = CommentResDto.from(comment);

        // mocking - comment
        given(commentRepository.save(any())).willReturn(comment);
        given(commentRepository.findById(commentId)).willReturn(Optional.ofNullable(comment));

        // when
        CommentResDto actualCommentResDto = commentCreator.save(saveCommentRequestDto, commentWriterId);

        // then
        Comment findComment = commentRepository.findById(actualCommentResDto.getId()).get();
        assertEquals(comment.getWriterId(), findComment.getWriterId());
        assertEquals(comment.getContent(), findComment.getContent());
        assertEquals(comment.isAnonymous(), findComment.isAnonymous());
        assertEquals(comment.getPost(), findComment.getPost());
        assertEquals(expectedCommentResDto, actualCommentResDto);
    }
}
