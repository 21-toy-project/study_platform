package toy.study_platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toy.study_platform.auth.entity.UserInfo;
import toy.study_platform.auth.repository.UserRepository;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    public UserInfo findById(String Id){
        return userRepository.findById(Id);
    }
}
