package toy.study_platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toy.study_platform.auth.entity.UserInfo;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     *  Authentication
     *  Spring Security UserDetails의 User객체를 반환
     *  User객체는 id, pw, auth(권한)으로 생성
     */
    @Override
    public UserDetails loadUserByUsername(String Id) throws UsernameNotFoundException {
        UserInfo userInfo = userService.findById(Id);
        return new User(userInfo.getId(), userInfo.getPw(), getAuthorities(userInfo));
    }

    /**
     *  Authorization
     *  사용자의 권한을 UserInfo에서 받음
     * @param userInfo
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(UserInfo userInfo){
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userInfo.getAuth());
        return authorities;
    }

}
