package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ScriptPaperDto {
    private String script_id;
    private String scene_id;
    private String video_name;
    private Date video_date;
    private Date video_time;
    private Date video_start_time;
    private String action_image;
    private String action_content;
    private String position_image;
    private String conti;
}
