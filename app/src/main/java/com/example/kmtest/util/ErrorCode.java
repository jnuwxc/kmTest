package com.example.kmtest.util;

/**
 * @author wxc
 * @date 2021.5.19
 */

public enum ErrorCode {
    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","添加用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试....");

    private String value;
    private String desc;

    private ErrorCode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + this.value + "]" + this.desc;
    }
}
