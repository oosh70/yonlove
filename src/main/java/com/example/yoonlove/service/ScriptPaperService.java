package com.example.yoonlove.service;

import com.example.yoonlove.dto.ScriptPaperDto;
import com.example.yoonlove.mapper.ScriptPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptPaperService {
    @Autowired
    private ScriptPaperMapper scenarioScriptPaper;

    //스크립트 페이퍼
    public ScriptPaperDto selectScriptPaper(){
        return scriptPaperMapper.selectScriptPaper();
    }
    public List<ScriptPaperDto> selectListScriptPaper() {
        return scriptPaperMapper.selectListScriptPaper();
    }
    //서비스 메소드 작성 CRUD
    public void insertScriptPaper(){
        scriptPaperMapper.insertScriptPaper();
    }
    public void updateScriptPaper(){
        scriptPaperMapper.updateScriptPaper();
    }
    public void deleteScriptPaper(){
        scriptPaperMapper.deleteScriptPaper();
    }

    //촬영타임테이블
    public TimeTableDto selectTimeTable(){
        return scriptPaperMapper.selectTimeTable();
    }
    public List<TimeTableDto> selectListTimeTable() {
        return scriptPaperMapper.selectListTimeTable();
    }
    //서비스 메소드 작성 CRUD
    public void insertTimeTable(){
        scriptPaperMapper.insertTimeTable();
    }
    public void updateTimeTable(){
        scriptPaperMapper.updateTimeTable();
    }
    public void deleteTimeTable(){
        scriptPaperMapper.deleteTimeTable();
    }
}
