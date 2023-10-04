package com.example.yoonlove.Dto;

import java.sql.Date;

public class NoticeDto {
    private int notice_id;
    private String notice_title;
    private String notice_content;
    private String notice_writer;
    private Date notice_date;
    private Date notice_update;
    private int notice_cnt;
    private String user_id;

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_content() {
        return notice_content;
    }

    public void setNotice_content(String notice_content) {
        this.notice_content = notice_content;
    }

    public String getNotice_writer() {
        return notice_writer;
    }

    public void setNotice_writer(String notice_writer) {
        this.notice_writer = notice_writer;
    }

    public Date getNotice_date() {
        return notice_date;
    }

    public void setNotice_date(Date notice_date) {
        this.notice_date = notice_date;
    }

    public Date getNotice_update() {
        return notice_update;
    }

    public void setNotice_update(Date notice_update) {
        this.notice_update = notice_update;
    }

    public int getNotice_cnt() {
        return notice_cnt;
    }

    public void setNotice_cnt(int notice_cnt) {
        this.notice_cnt = notice_cnt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
