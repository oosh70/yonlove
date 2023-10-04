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
}
