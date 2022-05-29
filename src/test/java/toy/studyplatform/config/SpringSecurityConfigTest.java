package toy.studyplatform.config;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import toy.studyplatform.auth.dto.CreateUserRequestDto;
import toy.studyplatform.auth.entity.UserInfo;
import toy.studyplatform.auth.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringSecurityConfigTest{
    @Autowired UserService userService;
    @Autowired MockMvc mockMvc;

    String id = "test1";
    String pw = "123";
    String name = "jaehee";
    String role = "ROLE_ADMIN";

    @BeforeEach
    public void 유저세팅(){
        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto(id, pw, name, role);
        userService.save(createUserRequestDto);
    }

    @Test
    @Order(1)
    @DisplayName("회원가입 확인")
    public void 회원가입_확인() throws Exception{
        UserInfo userInfo = userService.findById(id);
        assertThat(userInfo.getId()).isEqualTo(id);
        assertThat(userInfo.getPw()).isEqualTo(pw);
        assertThat(userInfo.getName()).isEqualTo(name);
        assertThat(userInfo.getRole()).isEqualTo(role);
    }

    @Test
    @Order(2)
    @DisplayName("USER 권한 로그인 가능성 여부")
    @WithMockUser(username = "test1", roles = "USER")
    public void USER_권한_로그인_가능성여부() throws Exception{
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(3)
    @DisplayName("ADMIN 권한 로그인 가능성 여부")
    @WithMockUser(username = "test2", roles = "ADMIN")
    public void ADMIN_권한_로그인_가능성여부() throws Exception{
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(4)
    @DisplayName("ADMIN api를 USER사용자가 실패하는지")
    @WithMockUser(username = "test3", roles = "USER")
    public void ADMIN_권한_api_USER사용자_실패여부() throws Exception{
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(5)
    @DisplayName("ADMIN api를 ADMIN사용자가 성공하는지")
    @WithMockUser(username = "test3", roles = "ADMIN")
    public void ADMIN_권한_api_ADMIN사용자_성공여부() throws Exception{
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}