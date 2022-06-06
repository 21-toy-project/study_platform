package toy.studyplatform.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.studyplatform.domain.post.dto.request.SavePostRequestDto;
import toy.studyplatform.domain.post.dto.response.SavePostResponseDto;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.jpa.PostRepository;

@Service
@Transactional
public class PostCreator {
    private final PostRepository postRepository;

    public PostCreator(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public SavePostResponseDto save(SavePostRequestDto savePostRequestDto, Long writerId) {
        Post savedPost = postRepository.save(savePostRequestDto.toEntity(writerId));
        return SavePostResponseDto.from(savedPost);
    }
}
