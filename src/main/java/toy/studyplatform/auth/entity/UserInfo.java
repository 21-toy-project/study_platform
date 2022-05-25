package toy.studyplatform.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import toy.studyplatform.common.entity.BaseTimeEntity;

@Entity
@Table(name = "USER")
public class UserInfo extends BaseTimeEntity {

    @Id @Column @GeneratedValue private Long userNo;

    @Column @NotNull private String id;

    @Column @NotNull private String pw;

    @Column @NotNull private String name;

    @Column @NotNull private String role;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String pw;
        private String name;
        private String role;

        private Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder pw(String pw) {
            this.pw = pw;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public UserInfo build() {
            UserInfo userInfo = new UserInfo();
            if (id.isEmpty() || pw.isEmpty() || name.isEmpty() || role.isEmpty()) {
                return null;
            }
            userInfo.id = id;
            userInfo.pw = pw;
            userInfo.name = name;
            userInfo.role = role;
            return userInfo;
        }
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getRole() {
        return role;
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getName() {
        return name;
    }
}
