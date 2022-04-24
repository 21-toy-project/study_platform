package toy.study_platform.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import toy.study_platform.domain.post.entity.Post;

import javax.validation.constraints.NotBlank;

public class PostRequestDto {
    @JsonProperty
    @NotBlank
    private String title;
    @JsonProperty
    @NotBlank
    private String content;

    public PostRequestDto() {
    }

    public PostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Long writerId) {
        return new Post.Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
    }
}
