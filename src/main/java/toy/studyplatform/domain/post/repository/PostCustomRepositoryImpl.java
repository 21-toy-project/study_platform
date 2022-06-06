package toy.studyplatform.domain.post.repository;

import java.util.List;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import toy.studyplatform.domain.comment.entity.QComment;
import toy.studyplatform.domain.post.dto.response.FindPostResponseSimpleDto;
import toy.studyplatform.domain.post.entity.QPost;

public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<FindPostResponseSimpleDto> findAllWithComments() {
        QPost qPost = QPost.post;
        QComment qComment = QComment.comment;
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                FindPostResponseSimpleDto.class,
                                qPost.id.as("id"),
                                qPost.title.as("title"),
                                qPost.writerId.as("writerId"),
                                qPost.createdDate.as("createdDate"),
                                ExpressionUtils.as(
                                        JPAExpressions.select(qComment.count())
                                                .from(qComment)
                                                .where(qComment.post.id.eq(qPost.id)),
                                        "countOfComments")))
                .from(qPost)
                .fetch();
    }
}
