package com.example.yoonlove.dto;

import lombok.Data;

@Data
public class FilmPlanDto {

    private String film_id;
    private String day_id;
    private String scene_id;
    private String inside_flag;
    private String day_flag;
    private String cut;

    //조인 테이블 : dayplan_film
    private String day_title;
    private String project_id;
    private String project_name;

}