package toy.study_platform.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.study_platform.auth.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findById(String Id);
}
