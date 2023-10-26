package com.example.yoonlove.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDayDto {
    private String day_id;
    private Date film_date;
    private String weather;
    private String day_title;
    private String light;
    private String costume;
    private String prop;
    private String film_order;
    private String direct;
    private String project_id;
    private Date plan_day_date;
}