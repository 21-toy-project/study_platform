package toy.studyplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration
class SpringSecurityConfigTest {
    @Autowired MockMvc mockMvc;

    @Test
    @DisplayName("USER 권한 로그인 가능성 여부")
    @WithMockUser(username = "test1", roles = "USER")
    public void USER_권한_로그인_가능성여부() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("ADMIN 권한 로그인 가능성 여부")
    @WithMockUser(username = "test2", roles = "ADMIN")
    public void ADMIN_권한_로그인_가능성여부() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @DisplayName("ADMIN api를 USER사용자가 실패하는지")
    @WithMockUser(username = "test3", roles = "USER")
    public void ADMIN_권한_api_USER사용자_실패여부() throws Exception {
        mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("ADMIN api를 ADMIN사용자가 성공하는지")
    @WithMockUser(username = "test3", roles = "ADMIN")
    public void ADMIN_권한_api_ADMIN사용자_성공여부() throws Exception {
        mockMvc.perform(get("/admin")).andDo(print()).andExpect(status().isOk());
    }
}
