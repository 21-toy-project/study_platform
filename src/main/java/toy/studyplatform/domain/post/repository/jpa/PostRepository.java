package toy.studyplatform.domain.post.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import toy.studyplatform.domain.post.entity.Post;
import toy.studyplatform.domain.post.repository.PostCustomRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {}
