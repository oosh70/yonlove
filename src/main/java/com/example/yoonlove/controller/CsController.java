package com.example.yoonlove.controller;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.service.CreatorService;
import com.example.yoonlove.service.CsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CsController {

    @Autowired
    private CsService csService;
    //서비스 객체 수정

    @GetMapping("/listnotice")
    public ModelAndView selectListNotice(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<NoticeDto> dto = csService.selectListNotice();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListNotice", dto);
        return mv;
    }

    @GetMapping("/selectnotice")
    public ModelAndView selectNotice(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        NoticeDto dto = csService.selectNotice();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectNotice", dto);
        return mv;
    }

    @GetMapping("/insertnotice")
    public ModelAndView insertNotice(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.insertNotice();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatenotice")
    public ModelAndView updateNotice(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.updateNotice();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

    @GetMapping("/deletenotice")
    public ModelAndView deleteNotice(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.deleteNotice();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }


    //QnA 메서드
    @GetMapping("/listqna")
    public ModelAndView selectListQnA(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<QnADto> dto = csService.selectListQnA();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListQnA", dto);
        return mv;
    }

    @GetMapping("/selectqna")
    public ModelAndView selectQnA(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        QnADto dto = csService.selectQnA();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectQnA", dto);
        return mv;
    }

    @GetMapping("/insertqna")
    public ModelAndView insertQnA(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.insertQnA();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updateqna")
    public ModelAndView updateQnA(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.updateQnA();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

    @GetMapping("/deleteqna")
    public ModelAndView deleteQnA(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        csService.deleteQnA();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
