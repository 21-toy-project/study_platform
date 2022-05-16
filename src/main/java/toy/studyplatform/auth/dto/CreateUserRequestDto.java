package toy.studyplatform.auth.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.auth.entity.UserInfo;

public class CreateUserRequestDto {
    @JsonProperty @NotBlank private String id;
    @JsonProperty @NotBlank private String pw;
    @JsonProperty @NotBlank private String name;
    @JsonProperty @NotBlank private String auth;

    private CreateUserRequestDto(String id, String pw, String name, String auth) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.auth = auth;
    }

    public UserInfo toEntity() {
        UserInfo userInfo = UserInfo.builder().id(id).pw(pw).name(name).auth(auth).build();
        return userInfo;
    }
}
