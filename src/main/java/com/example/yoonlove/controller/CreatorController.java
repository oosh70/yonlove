package com.example.yoonlove.controller;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
    public class CreatorController {

        @Autowired
        private CreatorService creatorservice;
        //서비스 객체 수정

        @GetMapping("/creator")
        public ModelAndView selectListCreaotr(){
            //실행할 메소드(서비스 부분에 있는 메소드)
            List<CreatorDto> dto = creatorservice.selectListCreator();

            //세션 객체생셩
            ModelAndView mv = new ModelAndView();
            //보여줄 view페이지 이름(ooo.mustache)
            mv.setViewName("/listcreator");

            //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
            mv.addObject("selectListCreator", dto);
            return mv;
        }
}
