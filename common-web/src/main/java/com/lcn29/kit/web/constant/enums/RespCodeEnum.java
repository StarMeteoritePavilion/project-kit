package com.lcn29.kit.web.constant.enums;

/**
 * <pre>
 *  unified http response Code
 * </pre>
 *
 * @author LCN
 * @date 2020-02-18 11:49
 */
public enum RespCodeEnum {

    /**
     * request handler success
     */
    SUCCESS(200, "request success"),

    /**
     * the args in the request verify fail
     */
    PARAMETER_ERROR(400, "args in request verify fail"),

    /**
     * the user is logout or the token expired
     */
    UNAUTHORIZED(401, "unauthorized"),

    /**
     * the user don't have the permission
     */
    NO_PERMISSION(403, "no permission"),

    /**
     * the args can't matches the condition of handler business
     */
    CONDITIONS_NOT_MATCHED(412, "the args not matches the condition"),

    /**
     * the error system not undefined
     */
    FAILURE(-999, "unknown fail");

    private int code;
    private String msg;

    RespCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

}
