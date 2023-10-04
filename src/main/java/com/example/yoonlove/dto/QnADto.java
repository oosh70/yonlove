package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class QnADto {
    private String qna_id;
    private String qna_title;
    private String qna_content;
    private String qna_writer;
    private Date qna_date;
    private Date qna_update;
    private int qna_cnt;
    private String user_id;

    public String getQna_id() {
        return qna_id;
    }

    public void setQna_id(String qna_id) {
        this.qna_id = qna_id;
    }

    public String getQna_title() {
        return qna_title;
    }

    public void setQna_title(String qna_title) {
        this.qna_title = qna_title;
    }

    public String getQna_content() {
        return qna_content;
    }

    public void setQna_content(String qna_content) {
        this.qna_content = qna_content;
    }

    public String getQna_writer() {
        return qna_writer;
    }

    public void setQna_writer(String qna_writer) {
        this.qna_writer = qna_writer;
    }

    public Date getQna_date() {
        return qna_date;
    }

    public void setQna_date(Date qna_date) {
        this.qna_date = qna_date;
    }

    public Date getQna_update() {
        return qna_update;
    }

    public void setQna_update(Date qna_update) {
        this.qna_update = qna_update;
    }

    public int getQna_cnt() {
        return qna_cnt;
    }

    public void setQna_cnt(int qna_cnt) {
        this.qna_cnt = qna_cnt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
