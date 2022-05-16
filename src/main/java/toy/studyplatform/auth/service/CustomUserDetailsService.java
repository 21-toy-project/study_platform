package toy.studyplatform.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import toy.studyplatform.auth.entity.UserInfo;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserInfo userInfo = userService.findById(id);
        return new User(userInfo.getId(), userInfo.getPw(), getAuthorities(userInfo));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserInfo userInfo){
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userInfo.getAuth());
        return authorities;
    }
}