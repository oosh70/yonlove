package com.example.yoonlove.service;

import com.example.yoonlove.dto.NoticeDto;
import com.example.yoonlove.dto.QnADto;
import com.example.yoonlove.mapper.CsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsService {
    @Autowired
    private CsMapper csMapper;


    //공지사항 서비스 메소드
    public NoticeDto selectNotice(){
        return csMapper.selectNotice();
    }
    public List<NoticeDto> selectListNotice() {
        return csMapper.selectListNotice();
    }
    //서비스 메소드 작성 CRUD
    public void insertNotice(){
        csMapper.insertNotice();
    }
    public void updateNotice(){
        csMapper.updateNotice();
    }
    public void deleteNotice(){
        csMapper.deleteNotice();
    }


    //QnA 서비스 메소드
    public QnADto selectQnA(){
        return csMapper.selectQnA();
    }
    public List<QnADto> selectListQnA(){
        return csMapper.selectListQnA();
    }
    public void insertQnA(){
        csMapper.insertQnA();
    }
    public void updateQnA(){
        csMapper.updateQnA();
    }
    public void deleteQnA(){
        csMapper.deleteQnA();
    }
}

