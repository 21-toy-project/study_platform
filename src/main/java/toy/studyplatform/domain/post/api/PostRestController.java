package toy.studyplatform.domain.post.api;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import toy.studyplatform.domain.post.dto.request.SavePostRequestDto;
import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;
import toy.studyplatform.domain.post.dto.response.SavePostResponseDto;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.service.PostCreator;
import toy.studyplatform.domain.post.service.PostReader;

@RestController
public class PostRestController {
    private final PostCreator postCreator;
    private final PostReader postReader;

    public PostRestController(PostCreator postCreator, PostReader postReader) {
        this.postCreator = postCreator;
        this.postReader = postReader;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<SavePostResponseDto> savePost(
            @Validated @RequestBody SavePostRequestDto dto) {
        // TODO: 2022-05-15 reqeust header의 토큰값에서 사용자 정보 파싱한 뒤 entity에 추가
        Long writerId = 0L;
        SavePostResponseDto savePostResponseDto = postCreator.save(dto, writerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePostResponseDto);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<FindPostResponseSimpleDto>> findAllPosts(Pageable pageable) {
        List<FindPostResponseSimpleDto> findPostResponseSimpleDtos = postReader.findAllPosts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(findPostResponseSimpleDtos);
    }

    @GetMapping("/api/post/{postId}")
    public ResponseEntity<Post> findPost(@PathVariable Long postId) {
        Post post = postReader.findPost(postId);
        if (post == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }
}
