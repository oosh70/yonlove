package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.CreatorDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CreatorMapper {
    List<CreatorDto> selectListCreator();
    CreatorDto selectCreator();
    CreatorDto insertCreator();
    CreatorDto updateCreator();
    CreatorDto deleteCreator();

}
