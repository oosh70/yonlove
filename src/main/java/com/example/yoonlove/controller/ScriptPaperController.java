package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.dto.TimeTableDto;
import com.example.yoonlove.service.ScriptPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScriptPaperController {
    @Autowired
    private ScriptPaperService scriptPaperService;

    //스크립트페이퍼
    @GetMapping("/listscriptpaper")
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

    @GetMapping("/selectscriptpaper")
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

    @GetMapping("/insertscriptpaper")
    public ModelAndView insertScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.insertScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatescriptpaper")
    public ModelAndView updateScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.updateScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/deletescriptpaper")
    public ModelAndView deleteScriptPaper(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.deleteScriptPaper();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

    //타입테이블
    @GetMapping("/listtimetable")
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
    @GetMapping("/updatetimetable")
    public ModelAndView updateTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.updateTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/deletetimetable")
    public ModelAndView deleteTimeTable(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scriptPaperService.deleteTimeTable();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
