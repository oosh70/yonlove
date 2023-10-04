package com.example.yoonlove.service;

import com.example.yoonlove.dto.CreatorDto;
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
    public CreatorDto selectCreator(){
        return creatorMapper.selectCreator();
    }

    public List<CreatorDto> selectListCreator(){
        return creatorMapper.selectListCreator();
    }
}
