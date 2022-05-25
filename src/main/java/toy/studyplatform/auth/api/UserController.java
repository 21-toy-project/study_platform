package toy.studyplatform.auth.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import toy.studyplatform.auth.dto.CreateUserRequestDto;
import toy.studyplatform.auth.dto.CreateUserResponseDto;
import toy.studyplatform.auth.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<CreateUserResponseDto> saveUser(
            @RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        CreateUserResponseDto createUserResponseDto = userService.save(createUserRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createUserResponseDto);
    }
}
