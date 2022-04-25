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
     */
    @Override
    public UserDetails loadUserByUsername(String ID) throws UsernameNotFoundException {
        UserInfo userInfo = userService.findById(ID);
        return new User(userInfo.getId(), userInfo.getPw(), getAuthorities(userInfo));
    }

    /**
     *  Authorization
     * @param userInfo
     * @return
     */
    private Collection<? extends GrantedAuthority> getAuthorities(UserInfo userInfo){
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userInfo.getAuth());
        return authorities;
    }

}
