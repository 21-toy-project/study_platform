package toy.studyplatform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import toy.studyplatform.auth.dto.CreateUserRequestDto;
import toy.studyplatform.auth.entity.Role;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration
public class UserRestControllerTest {
    @Autowired UserService userService;
    @Autowired MockMvc mockMvc;

    @BeforeEach
    public void 유저세팅() {
        CreateUserRequestDto createUserRequestDto =
                new CreateUserRequestDto("test1", "123", "jaehee", Role.ROLE_ADMIN);
        userService.save(createUserRequestDto);
    }

    @Test
    @DisplayName("중복 회원가입이 아닌 상황")
    public void 중복_회원가입이_아닌_상황() throws Exception {
        mockMvc
                .perform(get("/api/userid/test2/exists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("중복 회원가입인 상황")
    public void 중복_회원가입인_상황() throws Exception {
        mockMvc
                .perform(get("/api/userid/test1/exists"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
