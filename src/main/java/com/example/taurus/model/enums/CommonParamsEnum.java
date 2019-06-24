package com.example.taurus.model.enums;

public enum CommonParamsEnum {
    TEN(10),

    FIVE(5),

    NOT_FOUND(404),

    BYTE(1024);

    private Integer value;

    CommonParamsEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
