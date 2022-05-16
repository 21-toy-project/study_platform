package toy.studyplatform.auth.dto;

import toy.studyplatform.auth.entity.UserInfo;

import java.time.LocalDateTime;

public class UserResponseDto {
    private Long userNo;
    private String id;
    private String pw;
    private String name;
    private String auth;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public UserResponseDto(Long userNo, String id, String pw, String name, String auth, LocalDateTime createdDate, LocalDateTime modifiedDate){
        this.userNo = userNo;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.auth = auth;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static UserResponseDto from(UserInfo userInfo){
        final Long userNo = userInfo.getUserNo();
        final String id = userInfo.getId();
        final String pw = userInfo.getPw();
        final String name = userInfo.getName();
        final String auth = userInfo.getAuth();
        final LocalDateTime createdDate = userInfo.getCreatedDate();
        final LocalDateTime modifiedDate = userInfo.getModifiedDate();
        return new UserResponseDto(userNo, id, pw, name, auth, createdDate, modifiedDate);
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userNo=" + userNo +
                ", id='" + id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", auth='" + auth + '\'' +
                ", createDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
