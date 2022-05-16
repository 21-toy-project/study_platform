package toy.studyplatform.auth.entity;

import toy.studyplatform.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class UserInfo extends BaseTimeEntity {

    @Id
    @Column
    @GeneratedValue
    private Long userNo;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String auth;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String pw;
        private String name;
        private String auth;

        private Builder(){
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder pw(String pw){
            this.pw = pw;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder auth(String auth){
            this.auth = auth;
            return this;
        }

        public UserInfo build(){
            UserInfo userInfo = new UserInfo();
            if(id.isEmpty() || pw.isEmpty() || name.isEmpty() || auth.isEmpty()){
                return null;
            }
            userInfo.id = id;
            userInfo.pw = pw;
            userInfo.name = name;
            userInfo.auth = auth;
            return userInfo;
        }
    }


    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getAuth() {
        return auth;
    }

    public Long getUserNo() {
        return userNo;
    }

    public String getName() {
        return name;
    }
}
