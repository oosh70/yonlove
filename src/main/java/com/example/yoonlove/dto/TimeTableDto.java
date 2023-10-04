package com.example.yoonlove.dto;

import java.sql.Date;
public class TimeTableDto {
    private String table_id;
    private Date film_time;
    private int time_num;
    private String ok_ng;
    private Date slate_time;
    private String script_id;

    public String getTable_id() {
        return table_id;
    }

    public void setTable_id(String table_id) {
        this.table_id = table_id;
    }

    public Date getFilm_time() {
        return film_time;
    }

    public void setFilm_time(Date film_time) {
        this.film_time = film_time;
    }

    public int getTime_num() {
        return time_num;
    }

    public void setTime_num(int time_num) {
        this.time_num = time_num;
    }

    public String getOk_ng() {
        return ok_ng;
    }

    public void setOk_ng(String ok_ng) {
        this.ok_ng = ok_ng;
    }

    public Date getSlate_time() {
        return slate_time;
    }

    public void setSlate_time(Date slate_time) {
        this.slate_time = slate_time;
    }

    public String getScript_id() {
        return script_id;
    }

    public void setScript_id(String script_id) {
        this.script_id = script_id;
    }
}
