package toy.study_platform.domain.post.dto;

import toy.study_platform.domain.post.entity.Post;

public class PostSaveRequestDto {
    private String title;
    private String content;

    public Post toEntity(Long writerId) {
        return new Post.Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
    }
}
