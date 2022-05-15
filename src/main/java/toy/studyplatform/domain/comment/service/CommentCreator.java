package toy.studyplatform.domain.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import toy.studyplatform.domain.comment.dto.CommentResDto;
import toy.studyplatform.domain.comment.dto.SaveCommentRequestDto;
import toy.studyplatform.domain.comment.entity.Comment;
import toy.studyplatform.domain.comment.repository.CommentRepository;
import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.PostRepository;

@Service
@Transactional
public class CommentCreator {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentCreator(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentResDto save(SaveCommentRequestDto saveCommentRequestDto, Long writerId) {
        Post post = postRepository.getById(saveCommentRequestDto.getPostId());
        Comment savedComment = commentRepository.save(saveCommentRequestDto.toEntity(writerId, post));
        return CommentResDto.from(savedComment);
    }
}
