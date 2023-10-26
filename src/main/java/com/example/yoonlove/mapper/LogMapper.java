package com.example.yoonlove.mapper;

import com.example.yoonlove.dto.LogDto;
import com.example.yoonlove.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    public List<LogDto> selectListLog(PageDto pageInfo);

    public PageDto totalLogPost(PageDto dto);



    public LogDto selectLog(LogDto dto);
    public void insertLog(LogDto dto);
    public void updateLog(LogDto dto);
    public void deleteLog(LogDto dto);
}