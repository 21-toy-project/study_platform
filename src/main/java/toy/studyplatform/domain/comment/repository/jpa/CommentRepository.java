package toy.studyplatform.domain.comment.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import toy.studyplatform.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
