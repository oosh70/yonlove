package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
public class ScriptPaperController {
    @Autowired
    private ScriptPaperService scriptPaperService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private DropDownService dropDownService;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

    //스크립트페이퍼
    @GetMapping("script/scriptpaper")
    public ModelAndView selectListScriptPaper(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                              Principal user){
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("scriptpaper","script_id", page,pdto, companyId);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<ScriptPaperDto> dto = scriptPaperService.selectListScriptPaper(pageInfo);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/script");
        mv.addObject("selectListScriptPaper", dto);

        //페이징에 필요한센션
        mv.addObject("prefixUrl", "scriptpaper");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("script/{script_id}/selectscriptpaper") // 하단에 타임테이블 붙이기 !!!!
    public ModelAndView selectScriptPaper(ScriptPaperDto scriptPaperDto, @RequestParam(name="page", defaultValue="1") int page, PageDto pdto){
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper(scriptPaperDto);
        ModelAndView mv = new ModelAndView();

        //파일 없이 업로드해서 파일테이블이 생성이 안되 오류발생하는 부분을 처리//근본없는 해결방법인거 같음
        FileDto fileDto = fileService.selectScriptFile(scriptPaperDto);
        if(fileDto != null){
            mv.addObject("file",fileDto);
        }else{
            FileDto nullFileDto = new FileDto();
            nullFileDto.setFile_path(" ");
            mv.addObject("file",nullFileDto);
        }

        mv.setViewName("/script/scriptselect");
        mv.addObject("selectScriptPaper", dto);
        // 기존 값 셀럭트

        // 이어붙일 타임테이블 셀럭트 값
       // pdto.setPkid(dto.getTable_id());
        PageDto pageDto = new PageDto("timetable","table_id", page, pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
        String rink = pagingService.subPageRink(pageDto, "scriptpaper");

        List<TimeTableDto> subList = scriptPaperService.selectListTimeTable(pageInfo); //

        mv.addObject("selectListTimeTable", subList);

        //서브 페이징에 필요한섹션
        mv.addObject("pageDto", pageDto);
        mv.addObject("prefixUrl", "scriptpaper"); //컨트롤러 이름
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("script/insertscriptpaperview")
    public ModelAndView insertscript(Principal user) throws JsonProcessingException {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonListProject = dropDownService.dropDownOption("project",null, companyId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonListProject);
        mv.setViewName("/script/scriptinsert");
        return mv;
    }

    @PostMapping("script/insertscriptpaper")
    @ResponseBody
    public String insertScriptPaper(ScriptPaperDto dto){
        System.out.println(dto.toString());
        scriptPaperService.insertScriptPaper(dto);
        return "/script/scriptpaper";
    }

    @GetMapping("script/{script_id}/updatescriptview")
    public ModelAndView updatescript(ScriptPaperDto scriptPaperDto){
        ScriptPaperDto dto = scriptPaperService.selectScriptPaper(scriptPaperDto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/scriptupdate");
        mv.addObject("selectScriptPaper", dto);
        return mv;
    }

    @PostMapping("script/{script_id}/updatescriptpaper")
    @ResponseBody
    public String updateScriptPaper(ScriptPaperDto dto){
        scriptPaperService.updateScriptPaper(dto);
        return "/script/scriptpaper";
    }
    @PostMapping("script/{script_id}/deletescriptpaper")
    public String deleteScriptPaper(ScriptPaperDto dto){
        scriptPaperService.deleteScriptPaper(dto);
        return "redirect:/script/scriptpaper";
    }

    //타입테이블
    @GetMapping("script/timetable")
    public ModelAndView selectListTimeTable(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("timetable","table_id",page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);
        List<PageDto> pageList =pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(),page);
        String rink = pagingService.pageRink(pageDto);

        List<TimeTableDto> dto = scriptPaperService.selectListTimeTable(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetable");
        mv.addObject("selectListTimeTable", dto);

        //페이징에 필요한센션
        mv.addObject("prefixUrl", "script");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }

    @GetMapping("script/{table_id}/selecttimetable")
    public ModelAndView selectTimeTable(TimeTableDto timetableDto){
        TimeTableDto dto = scriptPaperService.selectTimeTable(timetableDto);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("script/timetableselect");
        mv.addObject("selectTimeTable", dto);
        return mv;
    }

    @GetMapping("script/inserttimeview/{script_id}")
    public ModelAndView insertTimeView(TimeTableDto dto){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/script/timetableinsert");

        mv.addObject("script_id",dto.getScript_id());
        return mv;
    }

    @PostMapping("script/inserttimetable")
    @ResponseBody
    public String insertTimeTable(TimeTableDto dto){
        scriptPaperService.insertTimeTable(dto);
        return "/script/"+dto.getScript_id()+"/selectscriptpaper";
    }

    @GetMapping("script/{table_id}/updatetimeview")
    public ModelAndView updateTimeView(TimeTableDto timeTableDto){
        TimeTableDto dto = scriptPaperService.selectTimeTable(timeTableDto);
        ModelAndView mv = new ModelAndView();
        HashMap<String, Boolean> okFlag = scriptPaperService.okFlagCheck(dto);
        mv.setViewName("script/timetableupdate");
        mv.addObject("okFlag", okFlag);
        mv.addObject("selectTimeTable", dto);
        return mv;
    }
    @PostMapping("script/{table_id}/updatetimetable")
    @ResponseBody
    public String updateTimeTable(TimeTableDto dto){
        scriptPaperService.updateTimeTable(dto);
        return "/script/"+dto.getScript_id()+"/selectscriptpaper";
    }

    @PostMapping("script/{table_id}/deletetimetable")
    public String deleteTimeTable(TimeTableDto dto){
        TimeTableDto timeTableDto = scriptPaperService.selectTimeTable(dto);
        scriptPaperService.deleteTimeTable(dto);
        return "redirect:/script/"+timeTableDto.getScript_id()+"/selectscriptpaper";
    }
}
