package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.mapper.FileMapper;
import com.example.yoonlove.service.FileService;
import com.example.yoonlove.service.PagingService;
import com.example.yoonlove.service.SceneService;
import lombok.extern.slf4j.Slf4j;
import com.example.yoonlove.service.ScriptPaperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class SceneController {
    @Autowired
    private SceneService sceneService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private ScriptPaperService scriptPaperService;


    @GetMapping("scene/scene")
    public ModelAndView selectListScene(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("scene","scene_id",page,pdto);

     /*   System.out.println("호재호재호재!!!!!!!!" + pageDto.toString());*/


        PageDto pageInfo = pagingService.paging(pageDto);


        System.out.println("호재호재호재!!!!!!!!" +pageInfo.toString());


        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);


        List<SceneDto> dto = sceneService.selectListScene(pageInfo);

        System.out.println("원인이 뭘까 도대체!!!!!"+dto.toString());

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/scene");
        mv.addObject("selectListScene", dto);
        mv.addObject("prefixUrl", "scene");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("scene/{scene_id}/selectscene")
    public ModelAndView selectScene(SceneDto sceneDto, @RequestParam(name="page", defaultValue = "1") int page, PageDto pdto ){
        FileDto fileDto = fileService.selectFile(sceneDto);
        SceneDto dto = sceneService.selectScene(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.addObject("file",fileDto);
        mv.setViewName("scene/sceneselect");
        mv.addObject("selectScene", dto);

        //서브게시판 리스트
        pdto.setPkid(dto.getScene_id());

        PageDto pageDto = new PageDto("scriptpaper","script_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.subPageRink(pageDto,"scene");

        List<ScriptPaperDto> subList = scriptPaperService.selectListScriptPaper(pageInfo);
        mv.addObject("selectListScriptPaper", subList);

        //서브 페이징에 필요한섹션
        mv.addObject("pageDto", pageDto);
        mv.addObject("prefixUrl", "scene"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("/scene/insertsceneview")
    public ModelAndView insertSceneView() throws JsonProcessingException{
        //fk값으로 db검색결과
        List<ScenarioDto> scenarioDto = sceneService.selectFk();

        //검색리스트를 json 리스트 문자열로 생성
        String jsonList = sceneService.fkJson(scenarioDto);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonList);
        mv.setViewName("/scene/sceneinsert");
        return mv;
    }

    @PostMapping("/scene/insertscene")
    public String insertScene(SceneDto dto, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println(dto.toString());
            sceneService.insertScene(dto);

            int lastnum = sceneService.lastPost(dto);

            if(file == null){
                System.out.println("업로드파일없음");
                fileService.insertNull(lastnum);
            }else {
                try {
                    System.out.println("있음");
                    fileService.insertFile(file, lastnum); // FileService를 사용하여 파일 업로드
                } catch (IOException e) {
                    log.info(e.getMessage());
                    // 예외 처리
                }
            }

        return "redirect:/scene/scene";
    }

    @GetMapping("/scene/{scene_id}/updatesceneview")
    public ModelAndView updateSceneView(SceneDto sceneDto){
        SceneDto dto = sceneService.selectScene(sceneDto);
        FileDto fileDto = fileService.selectFile(sceneDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/scene/sceneupdate");
        mv.addObject("updateScene",dto);
        mv.addObject("file", fileDto);
        return mv;
    }

    @PostMapping("/scene/{scene_id}/updatescene")
    public String updateScene(SceneDto dto, MultipartFile newfile) {

        try {
            fileService.updateFile(dto, newfile); // 파일수정해서 업로드하는 메소드!!!!!!!
        }catch (IOException e) {
            e.printStackTrace();

        }
        sceneService.updateScene(dto);
        return "redirect:/scene/scene";
    }
    @GetMapping("/scene/{scene_id}/deletescene")
    public String deleteScene(SceneDto dto){
        sceneService.deleteScene(dto);
        return "redirect:/scene/scene";
    }


    @GetMapping("/scene/{scene_id}/deletefile")
    public String deletefile(SceneDto dto){
        String id= dto.getScene_id();
        fileService.deleFile(dto);
        fileService.deletdb(dto);
        return "redirect:/scene/"+id+"/updatesceneview";
    }




    //출연자 정보
    @GetMapping("scene/actor")
    public ModelAndView selectListActor(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("actor","act_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ActorDto> dto = sceneService.selectListActor(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actor");
        mv.addObject("selectListActor", dto);

        mv.addObject("prefixUrl", "scene");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("scene/{act_id}/selectactor")
    public ModelAndView selectActor(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorselect");
        mv.addObject("selectActor", dto);
        return mv;
    }

    @GetMapping("scene/insertactorview")
    public String insertActorView(){
        return "scene/actorinsert";
    }
    @GetMapping("scene/insertactor")
    public String insertActor(ActorDto dto){
        sceneService.insertActor(dto);
        return "redirect:/scene/actor";
    }


    @GetMapping("scene/{act_id}/updateactorview")
    public ModelAndView updateActorView(ActorDto actorDto){
        ActorDto dto = sceneService.selectActor(actorDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("scene/actorupdate");
        mv.addObject("updateActor", dto);
        return mv;
    }

    @GetMapping("scene/{act_id}/updateactor")
    public String updateActor(ActorDto dto){
        sceneService.updateActor(dto);
        return "redirect:/scene/actor";
    }
    @GetMapping("scene/{act_id}/deleteactor")
    public String deleteActor(ActorDto dto){
        sceneService.deleteActor(dto);
        return "redirect:/scene/actor";
    }

}
