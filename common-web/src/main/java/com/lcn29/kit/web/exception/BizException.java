package com.lcn29.kit.web.exception;

/**
 * <pre>
 * Business exception
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 21:37
 */
public class BizException extends Exception {

    public BizException() {
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

}
