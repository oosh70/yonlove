package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.ActorDto;
import com.example.yoonlove.dto.FileDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.dto.ScenarioDto;
import com.example.yoonlove.dto.SceneDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SceneMapper {
    //스토리보드
    public List<SceneDto> selectListScene(PageDto pageInfo);
    public PageDto totalScenePost(PageDto dt);
    public SceneDto selectScene(SceneDto dto);
    public List<ScenarioDto> selectFk();
    public void insertScene(SceneDto dto);


    public void insertFile(FileDto fileDto);
    public void updateScene(SceneDto dto);
    public void deleteScene(SceneDto dto);

    //출연자편성
    public List<ActorDto> selectListActor(PageDto pageInfo);
    public PageDto totalActorPost(PageDto dt);
    public ActorDto selectActor(ActorDto dto);
    public ActorDto selectActorOne(ActorDto dto);
    public void insertActor(ActorDto dto);
    public void updateActor(ActorDto dto);
    public void deleteActor(ActorDto dto);


    //마지막 작성글 불러오는 메서드
    public int lastPost(SceneDto dto);
}
