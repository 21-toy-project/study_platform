package toy.studyplatform.domain.post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;
import toy.studyplatform.domain.post.repository.jpa.PostRepository;

@Service
@Transactional(readOnly = true)
public class PostReader {
    private final PostRepository postRepository;

    public PostReader(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<FindPostResponseSimpleDto> findAllPosts() {
        return postRepository.findAllWithComments();
    }
}
