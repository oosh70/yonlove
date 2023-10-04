package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ScenarioDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScriptPaperMapper {

    //스크립트
    public List<ScriptPaperDto> selectListScriptPaper();
    public ScriptPaperDto selectScenario();
    public void insertScriptPaper();
    public void updateScriptPaper();
    public void deleteScriptPaper();

    //타임테이블
    public List<TimeTableDto> selectListTimeTable();
    public TimeTableDto selectTimeTable();
    public void insertTimeTable();
    public void updateTimeTable();
    public void deleteTimeTable();
}
