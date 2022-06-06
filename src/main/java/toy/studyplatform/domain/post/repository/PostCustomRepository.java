package toy.studyplatform.domain.post.repository;

import java.util.List;

import com.querydsl.core.Tuple;
import org.springframework.data.domain.Pageable;

public interface PostCustomRepository {
    List<Tuple> findAllWithComments(Pageable pageable);
}
