package toy.studyplatform.domain.post.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import toy.studyplatform.domain.post.dto.PostResponseDto;
import toy.studyplatform.domain.post.dto.SavePostRequestDto;
import toy.studyplatform.domain.post.service.PostCreator;

@RestController
public class PostRestController {
    private PostCreator postCreator;

    public PostRestController(PostCreator postCreator) {
        this.postCreator = postCreator;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<PostResponseDto> savePost(@Validated @RequestBody SavePostRequestDto dto) {
        // TODO: 2022-05-15 reqeust header의 토큰값에서 사용자 정보 파싱한 뒤 entity에 추가
        Long writerId = 0L;
        PostResponseDto postResponseDto = postCreator.save(dto, writerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDto);
    }
}
