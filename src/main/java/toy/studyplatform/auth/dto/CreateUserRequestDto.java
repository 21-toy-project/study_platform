package toy.studyplatform.auth.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.auth.entity.Role;
import toy.studyplatform.auth.entity.UserInfo;

public class CreateUserRequestDto {
    @JsonProperty @NotBlank private String id;
    @JsonProperty @NotBlank private String pw;
    @JsonProperty @NotBlank private String name;
    @JsonProperty private Role role;

    public CreateUserRequestDto(String id, String pw, String name, Role role) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.role = role;
    }

    public UserInfo toEntity() {
        UserInfo userInfo = UserInfo.builder().id(id).pw(pw).name(name).role(role).build();
        return userInfo;
    }
}
