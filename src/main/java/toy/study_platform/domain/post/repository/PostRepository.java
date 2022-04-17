package toy.study_platform.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.study_platform.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
