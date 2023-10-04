package com.example.yoonlove.service;

import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.mapper.ScenarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioService {
    @Autowired
    private ScenarioMapper scenarioMapper;

    public ScenarioDto selectScenario(){
        return scenarioMapper.selectScenario();
    }
    public List<ScenarioDto> selectListScenario() {
        return scenarioMapper.selectListScenario();
    }
    //서비스 메소드 작성 CRUD
    public void insertScenario(){
        scenarioMapper.insertScenario();
    }
    public void updateScenario(){
        scenarioMapper.updateScenario();
    }
    public void deleteScenario(){
        scenarioMapper.deleteScenario();
    }
}
