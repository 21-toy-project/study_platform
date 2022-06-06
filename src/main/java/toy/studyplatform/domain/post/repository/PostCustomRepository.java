package toy.studyplatform.domain.post.repository;

import java.util.List;

import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;

public interface PostCustomRepository {
    List<FindPostResponseSimpleDto> findAllWithComments();
}
