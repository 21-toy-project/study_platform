package toy.studyplatform.auth.dto;

import java.time.LocalDateTime;

import toy.studyplatform.auth.entity.UserInfo;

public class UserResponseDto {
    private Long userNo;
    private String id;
    private String pw;
    private String name;
    private String auth;
    private LocalDateTime createdDate;

    public UserResponseDto(
            Long userNo, String id, String pw, String name, String auth, LocalDateTime createdDate) {
        this.userNo = userNo;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.auth = auth;
        this.createdDate = createdDate;
    }

    public static UserResponseDto from(UserInfo userInfo) {
        final Long userNo = userInfo.getUserNo();
        final String id = userInfo.getId();
        final String pw = userInfo.getPw();
        final String name = userInfo.getName();
        final String auth = userInfo.getAuth();
        final LocalDateTime createdDate = userInfo.getCreatedDate();
        return new UserResponseDto(userNo, id, pw, name, auth, createdDate);
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
                + ", auth='"
                + auth
                + '\''
                + ", createDate="
                + createdDate
                + '}';
    }
}
