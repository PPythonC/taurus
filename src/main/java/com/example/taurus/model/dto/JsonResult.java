package com.example.taurus.model.dto;

import lombok.Data;

/**
 * json格式
 */
@Data
public class JsonResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object result;
    /**
     * 返回数据的构造方法
     *
     *
     */
    public JsonResult(){}
    /**
     * 返回数据的构造方法
     *
     * @param code   状态码
     */
    public JsonResult(Integer code){this.code=code;}

    /**
     *
     * @param code
     * @param msg
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回数据的构造方法
     *
     * @param code   状态码
     * @param msg    信息
     * @param result 数据
     */
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    /**
     * 返回状态码和数据
     *
     * @param code   状态码
     * @param result 数据
     */
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }
}
