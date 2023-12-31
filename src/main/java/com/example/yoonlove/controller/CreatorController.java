package com.example.yoonlove.controller;

import com.example.yoonlove.dto.*;
import com.example.yoonlove.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
    public class CreatorController {

        @Autowired
        private CreatorService creatorservice;

        @Autowired
        private PagingService pagingService;
        @Autowired
        private YouTubeService youTubeService;
        @Autowired
        private UserService userService;
        @Autowired
        private AdminService adminService;
        @Autowired
        private VideoService videoService;


        @GetMapping("creator/creater")  // 뒤에 테이블명
        public ModelAndView selectListCreaotr(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                              Principal user){
            //유저정보 가저오는 dto
            UserDto userInfo = userService.getUser(user.getName());
            String companyId = userInfo.getCompany_id(); //회사 id 스트링
            //회사이름을 가져오는 객체
            CompanyDto companyDto = new CompanyDto();
            companyDto.setCompany_id(companyId);
            CompanyDto companyName = adminService.selectCompany(companyDto);

            PageDto pageDto = new PageDto("creater", "ch_id", page, pdto, companyId);
            PageDto pageInfo=pagingService.paging(pageDto);
            List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(), pageInfo.getPageEnd(), page);
            String rink = pagingService.pageRink(pageDto);

            List<CreatorDto> dto = creatorservice.selectListCreator(pageInfo);

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            for(int i = 0; i<dto.size(); i++){
                String formattedVideoCount = decimalFormat.format(dto.get(i).getVideocount());
                String formattedCh_sub =  decimalFormat.format(dto.get(i).getCh_sub());
                dto.get(i).setFormattedVideocount(formattedVideoCount);
                dto.get(i).setFormattedCh_sub(formattedCh_sub);
            }

            ModelAndView mv = new ModelAndView();
            mv.setViewName("creator/creator");
            mv.addObject("selectListCreator", dto);
            mv.addObject("companyName", companyName.getCompany_name());

            //페이징에 필요한센션
            mv.addObject("prefixUrl", "creator"); // 컨트롤러 앞부분 /명
            mv.addObject("paging", pageInfo);  //페이징정보
            mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
            mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
            return mv;
        }

        @PostMapping("creator/insertcreator")
        public String insertcreatorview(String userId, String channelId, String title) throws GeneralSecurityException, IOException {
            CreatorDto dto = youTubeService.channel(channelId);
            dto.setCh_name(title);
            dto.setUser_id(userId);
            creatorservice.insertCreator(dto);
            return "redirect:/creator/creater";
        }


      @GetMapping("/creator/{ch_id}/selectcreator")
      public ModelAndView selectCreator(CreatorDto creatorDto, Principal user,
                                        PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page) throws GeneralSecurityException, IOException {
          CreatorDto dto = creatorservice.selectCreator(creatorDto);
          ModelAndView mv = new ModelAndView();
          mv.setViewName("/creator/selectcreator");
          mv.addObject("selectCreator", dto);

          //dto의 조회수, 영상수, 구독자수를 쉼표를 포함한 문자열로 변환
          DecimalFormat decimalFormat = new DecimalFormat("#,###");
          String formattedVideoCount = decimalFormat.format(dto.getVideocount());
          String formattedViewCount = decimalFormat.format(dto.getViewcount());
          String formattedCh_sub =  decimalFormat.format(dto.getCh_sub());

          //변환값을 dto에 바인드
          dto.setFormattedVideocount(formattedVideoCount);
          dto.setFormattedViewcount(formattedViewCount);
          dto.setFormattedCh_sub(formattedCh_sub);


          //서브페이징 // 채널의 영상들을 자동으로 불러와서 db에 저장해서 최신화함
          videoService.videoInsert(creatorDto.getCh_id());

          //유저정보 가저오는 dto
          UserDto userInfo = userService.getUser(user.getName());
          String companyId = userInfo.getCompany_id(); //회사 id 스트링
          pdto.setPkid(dto.getCh_id());

          PageDto pageDto = new PageDto("video","video_id", page,pdto, companyId);
          pageDto.setCh_id(creatorDto.getCh_id()); //크리에이터 소속의 영상의 페이징을 하기위해, pageInfo에 chId를 넘겨줌
          PageDto pageInfo = pagingService.paging(pageDto); // paging ==> 전체게시글 갯수 구해오는 메소드
          List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page);
          String rink = pagingService.subPageRink(pageDto,"creator");
          List<VideoDto> contentList = videoService.selectListContent(pageInfo, creatorDto.getCh_id());
          mv.addObject("selectContentList", contentList);

          mv.addObject("pageDto", pageDto);
          mv.addObject("prefixUrl", "creator"); //컨트롤러 이름
          mv.addObject("paging", pageInfo);
          mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
          mv.addObject("pageRink",rink);
          // 기존 크레이터 값 끝
          return mv;
      }

    @GetMapping("/creator/updatecreatorview/{ch_id}") // 수정하는곳
    public ModelAndView updateCreatorView(CreatorDto creatorDto){
        ModelAndView mv = new ModelAndView();
        CreatorDto dto = creatorservice.selectCreator(creatorDto);
        mv.setViewName("/creator/updatecreator");
        mv.addObject("selectCreator", dto);
        return mv;
    }

     @PostMapping("/creator/updatecreator/{ch_id}") // 수정
     @ResponseBody
    public String updateCreator(CreatorDto dto){
            System.out.println("test111" +dto.toString());
        creatorservice.updateCreator(dto);
         System.out.println("test222" +dto.toString());
        return "/creator/creater";   // 목록페이지로 이동
    }

    @PostMapping("/creator/deletecreator/{ch_id}")
    public String deleteCreator(CreatorDto dto){
        creatorservice.deleteCreator(dto);
        return "redirect:/creator/creater";
    }

    //검색어로 채널 리스트 검색
    @PostMapping("/creator/search")
    public ModelAndView searchChannel(String searchKeyword, String userId) throws GeneralSecurityException, IOException {
        ArrayList<HashMap<String, String>> result = youTubeService.searchId(searchKeyword);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/creator/creatorinsert");
        mv.addObject("userId", userId);
        mv.addObject("hashmap", result);
        return mv;
    }
}
