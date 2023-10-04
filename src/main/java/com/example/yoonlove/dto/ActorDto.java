package com.example.yoonlove.dto;

public class ActorDto {
    private String actor_id;
    private String scene_id;
    private String pd_id;
    private String actor_info;

    public String getActor_id() {
        return actor_id;
    }

    public void setActor_id(String actor_id) {
        this.actor_id = actor_id;
    }

    public String getScene_id() {
        return scene_id;
    }

    public void setScene_id(String scene_id) {
        this.scene_id = scene_id;
    }

    public String getPd_id() {
        return pd_id;
    }

    public void setPd_id(String pd_id) {
        this.pd_id = pd_id;
    }

    public String getActor_info() {
        return actor_info;
    }

    public void setActor_info(String actor_info) {
        this.actor_info = actor_info;
    }
}
