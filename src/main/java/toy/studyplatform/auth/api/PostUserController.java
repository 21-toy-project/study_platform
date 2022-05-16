package toy.studyplatform.auth.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import toy.studyplatform.auth.dto.UserRequestDto;
import toy.studyplatform.auth.dto.UserResponseDto;
import toy.studyplatform.auth.service.UserService;

import javax.validation.Valid;

@RestController
public class PostUserController {
    private UserService userService;

    public PostUserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequestDto userRequestDto){
        System.out.println("user post contoller");
        UserResponseDto userResponseDto = userService.save(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

}
