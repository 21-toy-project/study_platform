package com.study.study_project.mapper;

import com.study.study_project.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //로그인
    UserVo getUserAccount(String userId);

    //회원가입
    void saveUser(UserVo userVo);
}
