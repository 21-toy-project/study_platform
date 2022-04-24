package toy.study_platform.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.study_platform.domain.post.dto.PostRequestDto;
import toy.study_platform.domain.post.dto.PostResponseDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;

@Service
@Transactional
public class PostCreator {
    private final PostRepository postRepository;

    public PostCreator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto save(PostRequestDto postRequestDto, Long writerId) {
        Post savedPost = postRepository.save(postRequestDto.toEntity(writerId));
        return PostResponseDto.from(savedPost);
    }
}
