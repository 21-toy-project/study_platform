package toy.studyplatform.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import toy.studyplatform.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {}
