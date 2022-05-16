package toy.studyplatform.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import toy.studyplatform.auth.entity.UserInfo;

import javax.validation.constraints.NotBlank;

public class UserRequestDto {
    @JsonProperty
    @NotBlank
    private String id;
    @JsonProperty
    @NotBlank
    private String pw;
    @JsonProperty
    @NotBlank
    private String name;
    @JsonProperty
    @NotBlank
    private String auth;

    private UserRequestDto(String id, String pw, String name, String auth){
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.auth = auth;
    }

    public UserInfo toEntity(){
        UserInfo userInfo = UserInfo.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .auth(auth)
                .build();
        return userInfo;
    }
}
