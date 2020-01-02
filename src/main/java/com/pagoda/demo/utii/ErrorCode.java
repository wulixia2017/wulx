package com.pagoda.demo.utii;

public enum ErrorCode {

    ERROR_CONVERT_DATE(100001,"date转换字符串错误");

    private Integer code;
    private String description;

    ErrorCode(Integer code, String description) {
        this.description = description;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
