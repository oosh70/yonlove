package com.example.yoonlove.service;

import com.example.yoonlove.dto.SceneDto;
import com.example.yoonlove.mapper.SceneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SceneService {
    @Autowired
    private SceneMapper sceneMapper;

    public SceneDto selectScene(){
        return sceneMapper.selectScene();
    }
    public List<SceneDto> selectListScene() {
        return sceneMapper.selectListScene();
    }
    //서비스 메소드 작성 CRUD
    public void insertScene(){
        sceneMapper.insertScene();
    }
    public void updateScene(){
        sceneMapper.updateScene();
    }
    public void deleteScene(){
        sceneMapper.deleteScene();
    }


//actor메소드
    public ActorDto selectScene(){
        return sceneMapper.selectActor();
    }
    public List<ActorDto> selectListScene() {
        return sceneMapper.selectListActor();
    }
    //서비스 메소드 작성 CRUD
    public void insertActor(){
        sceneMapper.insertActor();
    }
    public void updateActor(){
        sceneMapper.updateActor();
    }
    public void deleteActor(){
        sceneMapper.deleteActor();
    }
}
