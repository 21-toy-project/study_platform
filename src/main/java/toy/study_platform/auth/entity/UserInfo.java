package toy.study_platform.auth.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class UserInfo {

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

    @Column
    @CreationTimestamp
    private LocalDateTime appendDate;

    @Column
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Column
    private LocalDateTime deleteDate;

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getAuth() {
        return auth;
    }
}
