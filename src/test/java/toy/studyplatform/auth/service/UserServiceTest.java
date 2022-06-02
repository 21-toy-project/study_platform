package toy.studyplatform.auth.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import toy.studyplatform.auth.dto.CreateUserRequestDto;
import toy.studyplatform.auth.entity.Role;
import toy.studyplatform.auth.entity.UserInfo;

@SpringBootTest
class UserServiceTest {
    @Autowired UserService userService;

    private String id, pw, name;
    private Role role;

    @BeforeEach
    public void 유저세팅() {
        id = "test1";
        pw = "123";
        name = "jaehee";
        role = Role.ROLE_ADMIN;
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto(id, pw, name, role);
        userService.save(createUserRequestDto);
    }

    @Test
    @DisplayName("회원가입 확인")
    public void 회원가입_확인() throws Exception {
        UserInfo userInfo = userService.findById(id);
        assertThat(userInfo.getId()).isEqualTo(id);
        assertThat(userInfo.getPw()).isEqualTo(pw);
        assertThat(userInfo.getName()).isEqualTo(name);
        assertThat(userInfo.getRole()).isEqualTo(role);
    }
}
