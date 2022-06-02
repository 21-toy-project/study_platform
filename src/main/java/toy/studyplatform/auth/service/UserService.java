package toy.studyplatform.auth.service;

import org.springframework.stereotype.Service;

import toy.studyplatform.auth.dto.CreateUserRequestDto;
import toy.studyplatform.auth.dto.CreateUserResponseDto;
import toy.studyplatform.auth.entity.UserInfo;
import toy.studyplatform.auth.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponseDto save(CreateUserRequestDto createUserRequestDto) {
        UserInfo userInfo = userRepository.save(createUserRequestDto.toEntity());
        return CreateUserResponseDto.from(userInfo);
    }

    public UserInfo findById(String id) {
        return userRepository.findById(id);
    }

    public boolean checkIdDuplicate(String id) {
        return userRepository.existsById(id);
    }
}
