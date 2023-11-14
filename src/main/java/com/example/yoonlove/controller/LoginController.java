package com.example.yoonlove.controller;


import com.example.yoonlove.dto.CompanyDto;
import com.example.yoonlove.dto.UserDto;
import com.example.yoonlove.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    public UserService userService;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error, Model model){

        if (error != null) {
            model.addAttribute("errorMessage", "아이디와 비밀번호를 확인하세요.");
        }
          return "/main/login";
    }

    @GetMapping("companysignupview")
    public String companysignupview() {
        return "/login/companysignup";
    }

    @GetMapping("/companysignup")
    @ResponseBody
    public String companysign(CompanyDto dto, Principal user, UserDto userDto ) {
        //기업회원가입 로직 sql
        userService.companysignup(dto);
        String companyID = userService.lastCompanyID();
        //-------------테이블생성
        String userId = user.getName(); // 유저id
        userService.updateUserCompanyID(userId, companyID);
        return "/index";
    }



    @GetMapping("/test1")
    public void test(Principal user){

        String companyID = "company20";
        String userId = user.getName(); // 유저id
        userService.updateUserCompanyID(userId, companyID);
    }


    @PostMapping("ConfirmId")
    public ResponseEntity<Boolean> confirmId(@RequestParam("user_id") String user_id) {
        UserDto dto = new UserDto();
        dto.setUser_id(user_id);
        boolean isIdAvailable = userService.selectId(dto);
        return ResponseEntity.ok(isIdAvailable);
    }


    @GetMapping("signupview")
    public ModelAndView signview(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/login/signup");
        String test = "ture";
        mv.addObject("ConfirmId" , test);

        return mv;
    }

    @GetMapping("signup")
    @ResponseBody
    public String sign(UserDto dto){
        userService.signUp(dto);
        return "/cs/qna";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request,response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}