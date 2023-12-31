package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FilmPlanDto {

    private String film_id;
    private String day_id;
    private String scene_id;
    private String inside_flag;
    private String day_flag;
    private String cut;

    //추가 11.6 : 출연자편성과 조인으로 인해 추가
    private String act_id;

    //조인 테이블 : dayplan_film
    private String day_title;
    private String project_id;
    private String project_name;

    //일일촬영계획표 조인테이블 : day_table1
    private String scenario_name;
    private String scenario_num;
    private int scene_num;
    private String scene_content;
    private String scene_place;
    private String pd_id;
    private String pd_name;
    private String casting;
    private Date film_date;
}