package com.example.yoonlove.dto;

import java.sql.Date;

public class ScenarioDto {
    private String scenario_id;
    private int scenario_num;
    private String scenario_content;
    private String scenario_writer;
    private Date scenario_date;
    private String pj_id;

    public String getScenario_id() {
        return scenario_id;
    }

    public void setScenario_id(String scenario_id) {
        this.scenario_id = scenario_id;
    }

    public int getScenario_num() {
        return scenario_num;
    }

    public void setScenario_num(int scenario_num) {
        this.scenario_num = scenario_num;
    }

    public String getScenario_content() {
        return scenario_content;
    }

    public void setScenario_content(String scenario_content) {
        this.scenario_content = scenario_content;
    }

    public String getScenario_writer() {
        return scenario_writer;
    }

    public void setScenario_writer(String scenario_writer) {
        this.scenario_writer = scenario_writer;
    }

    public Date getScenario_date() {
        return scenario_date;
    }

    public void setScenario_date(Date scenario_date) {
        this.scenario_date = scenario_date;
    }

    public String getPj_id() {
        return pj_id;
    }

    public void setPj_id(String pj_id) {
        this.pj_id = pj_id;
    }
}
