package toy.studyplatform.domain.post.dto.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.domain.post.entity.Post;

public class SavePostRequestDto {
    @JsonProperty @NotBlank private String title;
    @JsonProperty @NotBlank private String content;

    public SavePostRequestDto() {}

    private SavePostRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static SavePostRequestDto of(String title, String content) {
        return new SavePostRequestDto(title, content);
    }

    public Post toEntity(Long writerId) {
        return Post.builder().title(title).content(content).writerId(writerId).build();
    }
}
