package com.example.yoonlove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScriptPaperController {
    @Autowired
    private SceneService sceneService;

    //스크립트 테이블
    @GetMapping("/listscript")
    public ModelAndView selectListScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<ScriptPaperDto> dto = scriptPaperService.selectListScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListScriptPaper", dto);
        return mv;
    }

    @GetMapping("/selectscript")
    public ModelAndView selectScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectScriptPaper", dto);
        return mv;
    }

    @GetMapping("/insertscript")
    public ModelAndView insertScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.insertScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatescript")
    public ModelAndView updateScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.updateScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

    //촬영테이블
    @GetMapping("/listtimetalbe")
    public ModelAndView selectListTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<TimeTableDto> dto = scriptPaperService.selectListTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListTimeTable", dto);
        return mv;
    }

    @GetMapping("/selecttimetable")
    public ModelAndView selectTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        TimeTableDto dto = scriptPaperService.selectTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectTimeTable", dto);
        return mv;
    }

    @GetMapping("/inserttimetable")
    public ModelAndView insertTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.insertTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatetimetalbe")
    public ModelAndView updateTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.updateTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
