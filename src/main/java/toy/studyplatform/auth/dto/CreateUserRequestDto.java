package toy.studyplatform.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import toy.studyplatform.auth.entity.Role;
import toy.studyplatform.auth.entity.UserInfo;

public class CreateUserRequestDto {
    @JsonProperty
    @NotBlank
    @Size(min = 5, max = 20, message = "아이디는 5자 이상 20자 이하로 입력해주세요.")
    private String id;

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
