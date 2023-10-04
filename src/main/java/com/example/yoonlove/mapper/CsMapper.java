package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsMapper {
    //공지사항 메소드
    public List<NoticeDto> selectListNotice();
    public NoticeDto selectNotice();
    public void insertNotice();
    public void updateNotice();
    public void deleteNotice();


    //QnA 메소드
    public List<QnADto> selectListQnA();
    public QnADto selectQnA();
    public void insertQnA();
    public void updateQnA();
    public void deleteQnA();
}
