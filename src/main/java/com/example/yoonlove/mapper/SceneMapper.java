package com.example.yoonlove.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public class SceneMapper {
    public List<SceneDto> selectListScene();
    public SceneDto selectScene();
    public void insertScene();
    public void updateScene();
    public void deleteScene();
    public List<ActorDto> selectListActor();
    public ActorDto selectScene();
    public void insertActor();
    public void updateActor();
    public void deleteActor();
}
