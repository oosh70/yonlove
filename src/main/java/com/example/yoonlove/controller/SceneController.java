package com.example.yoonlove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;

    //스토리보드 메소드
    @GetMapping("/listscene")
    public ModelAndView selectListScene(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<SceneDto> dto = sceneService.selectListScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListScene", dto);
        return mv;
    }

    @GetMapping("/selectscene")
    public ModelAndView selectScene(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        SceneDto dto = sceneService.selectScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectScene", dto);
        return mv;
    }

    @GetMapping("/insertscene")
    public ModelAndView insertScene(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.insertScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updatescene")
    public ModelAndView updateScene(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.updateScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }


    //엑터 메소드
    @GetMapping("/listactor")
    public ModelAndView selectListActor(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        List<ActorDto> dto = actorService.selectListActor();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/testlist");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectListActor", dto);
        return mv;
    }

    @GetMapping("/selectactor")
    public ModelAndView selectActor(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        ActorDto dto = actorService.selectActor();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");

        //dto객체 형태로 "selectListCreator"이라는 이름으로 세션형성
        mv.addObject("selectActor", dto);
        return mv;
    }

    @GetMapping("/insertactor")
    public ModelAndView insertactor(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        sceneService.insertScene();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }
    @GetMapping("/updateactor")
    public ModelAndView updateActor(){
        //실행할 메소드(서비스 부분에 있는 메소드)
        actorService.updateActor();

        //세션 객체생셩
        ModelAndView mv = new ModelAndView();
        //보여줄 view페이지 이름(ooo.mustache)
        mv.setViewName("/test");
        return mv;
    }

}
