package com.example.taurus.model.enums;

/**
 * 是否允许评论
 */
public enum AllowCommentEnum {
    /**
     * 允许
     */
    ALLOW(1),

    /**
     * 不允许
     */
    DISALLOW(1);
    private Integer code;
    AllowCommentEnum(Integer code){this.code=code;}
    public Integer getCode(){return code;}
}
