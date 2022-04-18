package toy.study_platform.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.study_platform.domain.post.dto.PostSaveRequestDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long add(PostSaveRequestDto postSaveRequestDto, Long writerId) {
        Post savedPost = postRepository.save(postSaveRequestDto.toEntity(writerId));
        return savedPost.getId();
    }
}
