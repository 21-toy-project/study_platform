package toy.study_platform.auth.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="USER")
public class UserInfo {

    @Id
    @Column
    @GeneratedValue
    private int UserNo;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String Pw;

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false)
    private String Auth;

    @Column
    @CreationTimestamp
    private LocalDateTime Append_Date;

    @Column
    @UpdateTimestamp
    private LocalDateTime Update_Date;

    @Column
    private LocalDateTime Delete_Date;

    public String getId() {
        return id;
    }

    public String getPw() {
        return Pw;
    }

    public String getAuth() {
        return Auth;
    }
}
