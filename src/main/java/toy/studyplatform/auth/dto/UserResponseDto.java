package toy.studyplatform.auth.dto;

import java.time.LocalDateTime;

import toy.studyplatform.auth.entity.UserInfo;

public class UserResponseDto {
    private Long userNo;
    private String id;
    private String pw;
    private String name;
    private String role;
    private LocalDateTime createdDate;

    public UserResponseDto(
            Long userNo, String id, String pw, String name, String role, LocalDateTime createdDate) {
        this.userNo = userNo;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.role = role;
        this.createdDate = createdDate;
    }

    public static UserResponseDto from(UserInfo userInfo) {
        final Long userNo = userInfo.getUserNo();
        final String id = userInfo.getId();
        final String pw = userInfo.getPw();
        final String name = userInfo.getName();
        final String role = userInfo.getRole();
        final LocalDateTime createdDate = userInfo.getCreatedDate();
        return new UserResponseDto(userNo, id, pw, name, role, createdDate);
    }

    @Override
    public String toString() {
        return "UserResponseDto{"
                + "userNo="
                + userNo
                + ", id='"
                + id
                + '\''
                + ", pw='"
                + pw
                + '\''
                + ", name='"
                + name
                + '\''
                + ", role='"
                + role
                + '\''
                + ", createDate="
                + createdDate
                + '}';
    }
}
