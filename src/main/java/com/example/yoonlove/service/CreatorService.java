package com.example.yoonlove.service;

import com.example.yoonlove.dto.CreatorDto;
import com.example.yoonlove.dto.PageDto;
import com.example.yoonlove.mapper.CreatorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreatorService {
    //Dto 객체 주입
    @Autowired
    private CreatorMapper creatorMapper;

    //서비스 메소드 작성 CRUD
    public CreatorDto selectCreator(CreatorDto creatorDto){
        return creatorMapper.selectCreator(creatorDto);
    }

    public List<CreatorDto> selectListCreator(PageDto dto){
        return creatorMapper.selectListCreator(dto);
    }
    public void insertCreator(CreatorDto creatorDto){
        creatorMapper.insertCreator(creatorDto);
    }

    public void updateCreator(CreatorDto creatorDto){
        creatorMapper.updateCreator(creatorDto);}

    public void deleteCreator(CreatorDto dto){ //게시글증가번호 = ch_id라서 creatorDto로 받음
        creatorMapper.deleteCreator(dto);}
}
