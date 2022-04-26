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

    public static PostRequestDto of(String title, String content) {
        PostRequestDto postRequestDto = new PostRequestDto();
        postRequestDto.title = title;
        postRequestDto.content = content;
        return postRequestDto;
    }

    public Post toEntity(Long writerId) {
        return new Post.Builder()
                .title(title)
                .content(content)
                .writerId(writerId)
                .build();
    }
}
