package toy.study_platform.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.study_platform.domain.post.dto.SavePostRequestDto;
import toy.study_platform.domain.post.dto.PostDto;
import toy.study_platform.domain.post.entity.Post;
import toy.study_platform.domain.post.repository.PostRepository;

@Service
@Transactional
public class PostCreator {
    private final PostRepository postRepository;

    public PostCreator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto save(SavePostRequestDto savePostRequestDto, Long writerId) {
        Post savedPost = postRepository.save(savePostRequestDto.toEntity(writerId));
        return PostDto.from(savedPost);
    }
}
