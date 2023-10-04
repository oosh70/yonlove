package com.example.yoonlove.controller;

import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.service.ScenarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ScenarioController {
    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/listscenario")
    public ModelAndView selectListScenario(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<ScenarioDto> dto = scenarioService.selectListScenario();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListScenario", dto);
        return mv;
    }

    @GetMapping("/selectscenario")
    public ModelAndView selectScenario(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        ScenarioDto dto = scenarioService.selectScenario();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectScenario", dto);
        return mv;
    }

    @GetMapping("/insertscenario")
    public ModelAndView insertScenario(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scenarioService.insertScenario();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatescenario")
    public ModelAndView updateScenario(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        scenarioService.updateScenario();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
}
