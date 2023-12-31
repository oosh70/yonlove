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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class PlanController {

    @Autowired
    private PlanService planService;
    @Autowired
    private PagingService pagingService;
    @Autowired
    private DropDownService dropDownService;
    @Autowired
    private CalendarService calendarService;
    @Autowired
    private UserService userService;


    @GetMapping("/plan/schedule_day")
    public ModelAndView selectListSchedule(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                           Principal user) {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("schedule_day","day_id", page,pdto, companyId);
        PageDto pageInfo = pagingService.paging(pageDto); // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);
        List<ScheduleDayDto> dto = planService.selectListSchedule(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/schedule_day");
        mv.addObject("scheduleList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }

    @GetMapping("plan/schedule/{day_id}")
    public ModelAndView selectSchedule(ScheduleDayDto dto) {
        ModelAndView mv = new ModelAndView();

        //테이블1 start : film_plan 조인 리스트
        List<FilmPlanDto> dayTable1 = planService.selectListDayTable1(dto.getDay_id());
        mv.addObject("table1",dayTable1);
        //테이블1 end

        //테이블2 start : schedule_time 조인 리스트
        List<ScheduleTimeDto> dayTable2 = planService.selectListDayTable2(dto.getDay_id());
        mv.addObject("table2",dayTable2);
        //테이블2 end

        //테이블3 start : actor_management : 출연자관리
        List<ActorManagementDto> dayTable3 = planService.selectListDayTable3(dto.getDay_id());
        mv.addObject("table3",dayTable3);
        //테이블3 end

        //일일촬영계획 코드  // 수정엄금
        ScheduleDayDto scheduleDetail = planService.selectSchedule(dto);
        mv.setViewName("/plan/scheduleDetail");
        mv.addObject("scheduleDetail", scheduleDetail);
        //일일촬영계획 end
        return mv;
    }

    @GetMapping("plan/insertScheduleView")
    public ModelAndView insertScheduleView(Principal user) throws JsonProcessingException{
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.dropDownOption("project",null, companyId);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/insertSchedule");
        mv.addObject("fkList", jsonList);
        return mv;
    }


    @PostMapping("plan/insertSchedule")  //컨텐츠 추가 처리
    @ResponseBody
    public String insertSchedule(ScheduleDayDto dto) {
        planService.insertSchedule(dto);
        return "/plan/schedule_day";
    }

    @GetMapping("plan/{day_id}/scheduleUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView scheduleUpdateView(ScheduleDayDto dto, Principal user) throws JsonProcessingException {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링
        String jsonList = dropDownService.dropDownOption("project",null, companyId);

        ScheduleDayDto scheduleDayDto = planService.selectSchedule(dto);
        //저장된 날씨의 값을 해쉬맵으로 저장, 해쉬맵은 머스테치에서 라디오 버튼 체크를 설정하게함
        HashMap<String, Boolean> weather = planService.weatherCheck(scheduleDayDto);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonList);
        mv.setViewName("/plan/updateSchedule");
        mv.addObject("updateSchedule", scheduleDayDto);
        mv.addObject("weather", weather);
        return mv;
    }

    @PostMapping("plan/{day_id}/updateSchedule") //업데이트 처리
    @ResponseBody
    public String updateSchedule(ScheduleDayDto dto) {
        System.out.println(dto.toString());
          planService.updateSchedule(dto);
        return "/plan/schedule_day";
    }

    @PostMapping("plan/{day_id}/deleteSchedule") //삭제 처리
    public String deleteSchedule( ScheduleDayDto dto) {
        planService.deleteSchedule(dto);
        return "redirect:/plan/schedule_day";

    }
//=====================================촬영계획표=================================================================================


    @GetMapping("plan/schedule_time")
    public ModelAndView selectListScheduleTime(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("schedule_time","time_id", page,pdto);  // page???
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<ScheduleTimeDto> dto = planService.selectListScheduleTime(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTimeList");
        mv.addObject("scheduleTimeList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }


    @GetMapping("plan/scheduleTime/{time_id}")
    public ModelAndView selectScheduleTime(ScheduleTimeDto dto) {
        ScheduleTimeDto scheduleDetail = planService.selectScheduleTime(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/scheduleTime");
        mv.addObject("scheduleTime", scheduleDetail);
        return mv;
    }

    @GetMapping("plan/insertScheduleTimeView")
    public ModelAndView insertScheduleTimeView(ScheduleTimeDto dto, Principal user) throws JsonProcessingException {
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.dropDownOption("project",null, companyId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("dayId", dto.getDay_id());
        mv.addObject("fkList", jsonList);
        mv.setViewName("/scene/sceneinsert");
        mv.setViewName("plan/insertScheduleTime");
        return mv;
    }


    @PostMapping("plan/insertScheduleTime")  //컨텐츠 추가 처리
    @ResponseBody
    public String insertTime(ScheduleTimeDto dto) {
        planService.insertTime(dto);
        return "/plan/schedule/"+dto.getDay_id();
    }


    @GetMapping("plan/{time_id}/scheduleTimeUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView scheduleTimeUpdateView(ScheduleTimeDto dto) {
        ScheduleTimeDto scheduleTimeDto = planService.selectScheduleTime(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/updateScheduleTime");
        mv.addObject("updateScheduleTime", scheduleTimeDto);
        return mv;
    }

    @PostMapping("plan/{time_id}/updateScheduleTime") //업데이트 처리
    @ResponseBody
    public String updateTime( ScheduleTimeDto dto) {
        //리다이렉트할 url에 들어갈 값을 추출
        ScheduleTimeDto scheduleTimeDto = planService.selectScheduleTime(dto);
        planService.updateTime(dto);

        return "/plan/schedule/" + scheduleTimeDto.getDay_id();
    }

    @PostMapping("plan/{time_id}/deleteTime") //삭제 처리
    @ResponseBody
    public String deleteTime(ScheduleTimeDto dto) {
        String day_id = planService.searchDayId(dto.getTime_id());
        planService.deleteTime(dto);
        return "/plan/schedule/"+day_id;
    }

//===================================출연자관리=========================================================


    @GetMapping("/plan/actor_management")
    public ModelAndView selectListActorManagement(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page){
        PageDto pageDto = new PageDto("actor_management","actor_id", page,pdto);
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<ActorManagementDto> dto = planService.selectListActorManagement(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementList");
        mv.addObject("actorManagementList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달
        return mv;
    }


    @GetMapping("plan/actorManagement/{actor_id}")
    public ModelAndView selectActorManagement(ActorManagementDto dto) {
        ActorManagementDto actorManagementDetail = planService.selectActorManagement(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementDetail");
        mv.addObject("actorManagementDetail", actorManagementDetail);
        return mv;
    }

   @GetMapping("plan/insertactorManagementView")
    public ModelAndView insertactorManagementView(ScheduleDayDto dto, Principal user) throws JsonProcessingException{
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.dropDownOption("table1",null, companyId);

        ModelAndView mv = new ModelAndView();
        mv.addObject("fkList", jsonList);
        mv.addObject("dayId", dto.getDay_id());
        mv.setViewName("plan/insertactorManagementView");
        return mv;
    }


    @PostMapping("plan/insertactorManagement")  //컨텐츠 추가 처리
    @ResponseBody
    public String insertActorManagement(ActorManagementDto dto) {
        String dayId = dto.getDay_id();
        //pd_id로 proudce를 검색해서 출연자 이름을 획득하고 dto에 바인드
        String actorName = planService.searchActorName(dto.getPd_id());
        dto.setActor_name(actorName);

        planService.insertActorManagement(dto);
        return "/plan/schedule/"+dayId;
    }


    @GetMapping("plan/{actor_id}/actorManagementUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView actorManagementUpdateView(ActorManagementDto dto) {
        ActorManagementDto actorManagementDto = planService.selectActorManagement(dto);//업데이트를 하려면 해당 컨텐츠 불러와야하니까 위에 selectContent메소드를 다시씀!
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/actorManagementUpdateView");
        mv.addObject("actorManagementUpdate", actorManagementDto);
        return mv;
    }

    @PostMapping("plan/{actor_id}/updateActorManagement") //업데이트 처리
    @ResponseBody
    public String updateActorManagement(ActorManagementDto dto) {
        //수정후 리다이렉션 할 url의 값을 추출
        ActorManagementDto actorManagementDto = planService.selectActorManagement(dto);
        planService.updateActorManagement(dto);
        return "/plan/schedule/" + actorManagementDto.getDay_id();
    }

    @PostMapping("plan/{actor_id}/deleteActorManagement") //삭제 처리
    @ResponseBody
    public String deleteActorManagement(ActorManagementDto dto) {
        ActorManagementDto actorManagementDto = planService.selectActorManagement(dto);
        planService.deleteActorManagement(dto);
        return "/plan/schedule/"+ actorManagementDto.getDay_id();
    }

//===========================================================================================출연자 관리 end

    @GetMapping("plan/film_plan")
    public ModelAndView selectListFilmPlan(PageDto pdto, @RequestParam(name="page", defaultValue = "1") int page,
                                           Principal user){
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        PageDto pageDto = new PageDto("film_plan","film_id", page,pdto, companyId);
        PageDto pageInfo = pagingService.paging(pageDto);

        // paging ==> 전체게시글 갯수 구해오는 메소드
        List<PageDto> pageList = pagingService.pageList(pageInfo.getPageStart(),pageInfo.getPageEnd(),page); // pageList==> 뷰페이지에 페이징 리스트를 생성해주는 리스트 메소드
        String rink = pagingService.pageRink(pageDto);

        List<FilmPlanDto> dto = planService.selectListFilmPlan(pageInfo);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmList");
        mv.addObject("filmList", dto);

        mv.addObject("prefixUrl","plan");
        mv.addObject("paging", pageInfo);  //페이징정보
        mv.addObject("pagelist", pageList); //페이지 하단부 페이지 리스트
        mv.addObject("pageRink",rink); //검색유무에 다라 동적 페이지링크를 뷰페이지에 전달

        return mv;
    }


    @GetMapping("plan/filmPlan/{film_id}")
    public ModelAndView selectFilmPlan(FilmPlanDto dto) {
        FilmPlanDto filmPlanDto = planService.selectFilmPlan(dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("/plan/filmPlanDetail");
        mv.addObject("filmPlanDetail", filmPlanDto);
        return mv;
    }

    @GetMapping("plan/insertFilmPlanView/{day_id}")
    public ModelAndView insertFilmPlanView(ScheduleDayDto dto, Principal user) throws JsonProcessingException{
        ModelAndView mv = new ModelAndView();

        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        String jsonList = dropDownService.dropDownOption("project",null, companyId);
        mv.addObject("fkList", jsonList);
        mv.addObject("dayId", dto.getDay_id());
        mv.setViewName("plan/insertFilmPlanView");
        return mv;
    }


    @PostMapping("plan/insertFilm")  //컨텐츠 추가 처리
    @ResponseBody
    public String insertFilm(FilmPlanDto dto) {
        //dto에는 insert에 들어갈 act_id가 없음. dto에 있는 pd_id와 scene_id로 act_id를 획득하는 로직
        String actId = planService.selectFilmJoinActID(dto.getPd_id(), dto.getScene_id());// day_id 값이 여러개가 나와서
        dto.setAct_id(actId);   //획득된 act_id를 dto에 바인드
        planService.insertFilm(dto);
        return "/plan/schedule/"+dto.getDay_id();
    }

    @GetMapping("plan/{film_id}/filmPlanUpdateView") //컨텐츠 업데이트하는 뷰
    public ModelAndView filmPlanUpdateView(FilmPlanDto dto) {
        FilmPlanDto filmPlanDto = planService.selectFilmPlan(dto);
        ModelAndView mv = new ModelAndView();

        HashMap<String, Boolean> insideFlag = planService.insideFlagCheck(filmPlanDto);
        HashMap<String, Boolean> dayFlag = planService.dayFlagCheck(filmPlanDto);

        mv.addObject("insideFlag",insideFlag);
        mv.addObject("dayFlag",dayFlag);

        mv.setViewName("/plan/filmPlanUpdateView");
        mv.addObject("filmPlanUpdate", filmPlanDto);
        return mv;
    }

    @PostMapping("plan/{film_id}/updatefilm") //업데이트 처리
    @ResponseBody
    public String updateFilm(FilmPlanDto dto) {
        FilmPlanDto filmPlanDto = planService.selectFilmPlan(dto);
        planService.updateFilm(dto);
        return "/plan/schedule/"+filmPlanDto.getDay_id();
    }

    @PostMapping("plan/{film_id}/deleteFilm") //삭제 처리
    @ResponseBody
    public String deleteFilm(FilmPlanDto dto) {
        FilmPlanDto filmPlanDto = planService.selectFilmPlan(dto);
        planService.deleteFilm(dto);
        return "/plan/schedule/"+filmPlanDto.getDay_id();
    }

    //------------------------월력형 테이블 로직---------------------------------//
    @GetMapping("/calendar")
    public ModelAndView showCalendar(@RequestParam(defaultValue = "-1") int year, @RequestParam(defaultValue = "-1")int month,
                                     Principal user) throws JsonProcessingException {
        //유저정보 가저오는 dto
        UserDto userInfo = userService.getUser(user.getName());
        String companyId = userInfo.getCompany_id(); //회사 id 스트링

        //년월 기본값(-1년-1월) 이라면 현재 년월 기준으로 year/month 값 출력
        if(year+month == -2){
            LocalDate currentDate = LocalDate.now();
            year= currentDate.getYear();
            month = currentDate.getMonthValue();
        }
        ModelAndView mv = new ModelAndView();
        List<List<String>> calendarData = calendarService.generateCalendarData(year,month);

        //제작일지 Json 불러오기 : {log1 : 작성일자} 식으로 존재함
        String calendarLog = calendarService.logJson(year,month,companyId);
        mv.addObject("logJson",calendarLog);

        //촬영일정표 Json 불러오기 : {day1 : 작성일자} 식으로 존재함
        String calendarDay = calendarService.dayJson(year,month,companyId);
        mv.addObject("dayJson",calendarDay);

        // 뷰로 데이터를 전달하기 위해 모델에 "calendar" 속성 추가
        mv.setViewName("/plan/calendar");
        mv.addObject("month", month);
        mv.addObject("year", year);
        mv.addObject("calendar", calendarData);
        return mv;
    }

    //-----------------Post방식(로직이 길이서 안씀) 구현만 함-----------------//
    //전월 버튼 눌럿을때 응답
    @PostMapping("/calendar/premonth")
    @ResponseBody
    public Map<String, Object> preMonth(int year, int month){

        //1월이 되면 전년도의 12월로 입력해주는 로직
        int preMonth = month-1;
        int resultYear = year;
        if(preMonth < 1){
            resultYear = year-1;
            preMonth = 12;
        }

        Map<String, Object> response = new HashMap<>();
        List<List<String>> calendarData = calendarService.generateCalendarData(resultYear,preMonth);

        response.put("year", resultYear);
        response.put("month", preMonth);
        response.put("calendar", calendarData);

        return response;
    }

    //전년 버튼 눌럿을때 응답
    @PostMapping("/calendar/preyear")
    @ResponseBody
    public Map<String, Object> preYear(int year, int month){
        //현재 년에서 1을 뺌
        year = year - 1;

        Map<String, Object> response = new HashMap<>();
        List<List<String>> calendarData = calendarService.generateCalendarData(year,month);

        response.put("year", year);
        response.put("month", month);
        response.put("calendar", calendarData);

        return response;
    }

    //다음달 버튼 눌럿을때 응답
    @PostMapping("/calendar/nextmonth")
    @ResponseBody
    public Map<String, Object> nextMonth(int year, int month){

        //1월이 되면 전년도의 12월로 입력해주는 로직
        int nextMonth = month+1;
        int resultYear = year;
        if(nextMonth > 12){
            resultYear = year + 1;
            nextMonth = 1;
        }

        Map<String, Object> response = new HashMap<>();
        List<List<String>> calendarData = calendarService.generateCalendarData(resultYear,nextMonth);

        response.put("year", resultYear);
        response.put("month", nextMonth);
        response.put("calendar", calendarData);

        return response;
    }

    //내년 버튼 눌럿을때 응답
    @PostMapping("/calendar/nextyear")
    @ResponseBody
    public Map<String, Object> nextYear(int year, int month){
        //현재 년에서 1을 더함
        year = year + 1;

        Map<String, Object> response = new HashMap<>();
        List<List<String>> calendarData = calendarService.generateCalendarData(year,month);

        response.put("year", year);
        response.put("month", month);
        response.put("calendar", calendarData);

        return response;
    }
}
