package toy.studyplatform.domain.post.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.studyplatform.domain.comment.repository.jpa.CommentRepository;
import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.jpa.PostRepository;

@Service
@Transactional(readOnly = true)
public class PostReader {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostReader(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public List<FindPostResponseSimpleDto> findAllPosts(
            @PageableDefault(size = 10) Pageable pageable) {
        return postRepository.findAllWithComments(pageable).stream()
                .map(
                        tuple ->
                                FindPostResponseSimpleDto.from(
                                        Objects.requireNonNull(tuple.get(0, Post.class)), tuple.get(1, Long.class)))
                .collect(Collectors.toList());
    }

    public Post findPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }
}
