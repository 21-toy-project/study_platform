package toy.studyplatform.domain.post.repository;

import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import toy.studyplatform.domain.comment.entity.QComment;
import toy.studyplatform.domain.post.entity.QPost;

public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Tuple> findAllWithComments(Pageable pageable) {
        QPost qPost = QPost.post;
        QComment qComment = QComment.comment;
        return jpaQueryFactory
                .select(
                        qPost,
                        JPAExpressions.select(qComment.count())
                                .from(qComment)
                                .where(qComment.post.id.eq(qPost.id)))
                .from(qPost)
                .orderBy(qPost.createdDate.desc())
                .offset(0)
                .limit(pageable.getPageSize())
                .fetch();
    }
}
