package toy.studyplatform.domain.comment.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import toy.studyplatform.domain.comment.dto.CommentResDto;
import toy.studyplatform.domain.comment.dto.SaveCommentRequestDto;
import toy.studyplatform.domain.comment.service.CommentCreator;

@RestController
public class CommentRestController {
    private CommentCreator commentCreator;

    public CommentRestController(CommentCreator commentCreator) {
        this.commentCreator = commentCreator;
    }

    @PostMapping("/api/comments")
    public ResponseEntity<CommentResDto> saveComment(
            @Validated @RequestBody SaveCommentRequestDto commentRequestDto) {
        // TODO: 2022-05-15 reqeust header의 토큰값에서 사용자 정보 파싱한 뒤 entity에 추가
        Long writerId = 0L;
        CommentResDto commentResDto = commentCreator.save(commentRequestDto, writerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResDto);
    }
}
