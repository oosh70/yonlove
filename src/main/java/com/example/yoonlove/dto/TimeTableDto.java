package com.example.yoonlove.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TimeTableDto {
    private String table_id;
    private String film_time;
    private int time_num;
    private String ok_ng;
    private String slate_time;
    private String script_id;

    @Override
    public String toString() {
        return "TimeTableDto{" +
                "table_id='" + table_id + '\'' +
                ", film_time=" + film_time +
                ", time_num=" + time_num +
                ", ok_ng='" + ok_ng + '\'' +
                ", slate_time=" + slate_time +
                ", script_id='" + script_id + '\'' +
                '}';
    }
}
