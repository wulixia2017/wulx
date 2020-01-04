package com.pagoda.demo.entity;

import com.pagoda.platform.dto.BaseEntity;


public class Keywordrecord implements BaseEntity{
    private Integer id;
    private String keyrecord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyrecord() {
        return keyrecord;
    }

    public void setKeyrecord(String keyrecord) {
        this.keyrecord = keyrecord;
    }
}
