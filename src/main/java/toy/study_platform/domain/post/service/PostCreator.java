package toy.study_platform.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.study_platform.domain.post.dto.PostRequestDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;

@Service
@Transactional
public class PostCreator {
    private final PostRepository postRepository;

    public PostCreator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(PostRequestDto postRequestDto, Long writerId) {
        return postRepository.save(postRequestDto.toEntity(writerId));
    }
}
