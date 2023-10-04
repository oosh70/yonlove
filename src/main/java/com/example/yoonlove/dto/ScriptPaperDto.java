package com.example.yoonlove.dto;

import java.sql.Date;
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

    public String getScript_id() {
        return script_id;
    }

    public void setScript_id(String script_id) {
        this.script_id = script_id;
    }

    public String getScene_id() {
        return scene_id;
    }

    public void setScene_id(String scene_id) {
        this.scene_id = scene_id;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public Date getVideo_date() {
        return video_date;
    }

    public void setVideo_date(Date video_date) {
        this.video_date = video_date;
    }

    public Date getVideo_time() {
        return video_time;
    }

    public void setVideo_time(Date video_time) {
        this.video_time = video_time;
    }

    public Date getVideo_start_time() {
        return video_start_time;
    }

    public void setVideo_start_time(Date video_start_time) {
        this.video_start_time = video_start_time;
    }

    public String getAction_image() {
        return action_image;
    }

    public void setAction_image(String action_image) {
        this.action_image = action_image;
    }

    public String getAction_content() {
        return action_content;
    }

    public void setAction_content(String action_content) {
        this.action_content = action_content;
    }

    public String getPosition_image() {
        return position_image;
    }

    public void setPosition_image(String position_image) {
        this.position_image = position_image;
    }

    public String getConti() {
        return conti;
    }

    public void setConti(String conti) {
        this.conti = conti;
    }
}
