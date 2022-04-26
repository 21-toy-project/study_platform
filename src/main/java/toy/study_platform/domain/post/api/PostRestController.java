package toy.study_platform.domain.post.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.study_platform.domain.post.dto.SavePostRequestDto;
import toy.study_platform.domain.post.dto.PostDto;
import toy.study_platform.domain.post.service.PostCreator;

@RestController
public class PostRestController {
    private PostCreator postCreator;

    public PostRestController(PostCreator postCreateService) {
        this.postCreator = postCreateService;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<?> savePost(@Validated @RequestBody SavePostRequestDto dto) {
        // reqeust header의 토큰값에서 사용자 정보 파싱한 뒤 entity에 추가
        Long writerId = 0L;
        PostDto postDto = postCreator.save(dto, writerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDto);
    }
}
