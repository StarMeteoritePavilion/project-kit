package com.lcn29.kit.web.response;

import com.lcn29.kit.web.constant.enums.RespCodeEnum;

import java.io.Serializable;

/**
 * <pre>
 * unified http response entity
 * </pre>
 *
 * @author LCN
 * @date 2020-02-18 11:59
 */
public class Response<T> implements Serializable {

    /**
     * the http code
     */
    private int code;

    /**
     * hint msg
     */
    private String msg;

    /**
     * response data
     */
    private T body;

    public Response() {
        this.code = RespCodeEnum.SUCCESS.getCode();
        this.msg = RespCodeEnum.SUCCESS.getMsg();
    }

    public Response(RespCodeEnum respCodeEnum) {
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
    }

    public Response(RespCodeEnum respCodeEnum, T body) {
        this.code = respCodeEnum.getCode();
        this.msg = respCodeEnum.getMsg();
        this.body = body;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
