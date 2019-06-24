package com.example.taurus.model.enums;

/**
 *
 */
public enum LocaleEnum {
    /**
     * 中文
     */
    ZH_CN("zh_CN"),

    /**
     * 英语
     */
    EN_US("en_US");

    private String value;

    LocaleEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
