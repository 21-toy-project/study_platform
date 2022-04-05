package com.study.study_project.controller;

import com.study.study_project.service.UserService;
import com.study.study_project.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserController {
//    @Autowired
//    UserService userService;
    private final UserService userService;

    /**
     * localhost:9090(현재 포트9090) 시 login으로 redirect
     * @return
     */
    @GetMapping
    public String root(){
        return "redirect:/login";
    }

    /**
     * 로그인 폼
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 로그인 실패 폼
     * @return
     */
    @GetMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    /**
     * 회원가입 폼
     * @return
     */
    @GetMapping("/signUp")
    public String signUpForm(){
        return "signUp";
    }

    /**
     * 회원가입 진행
     * @param userVo
     * @return
     */
    @PostMapping("/signUp")
    public String signUp(UserVo userVo){
        userService.joinUser(userVo);
        return "redirect:/login";
    }

    /**
     * 유저 페이지
     * @param model
     * @param authentication
     * @return
     */
    @GetMapping("user_access")
    public String userAccess(Model model, Authentication authentication){
        //Authentication 객체를 통해 유저 정보를 가져올 수 있음
        UserVo userVo = (UserVo) authentication.getPrincipal(); // userDetail 객체를 가져옴
        model.addAttribute("info", userVo.getUserId() + "의 "+ userVo.getUserName()+ "님");
        return "user_access";
    }
}
