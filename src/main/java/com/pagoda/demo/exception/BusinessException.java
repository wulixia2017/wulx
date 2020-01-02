package com.pagoda.demo.exception;

import com.pagoda.demo.utii.ErrorCode;

/**
 * 业务异常信息
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 6401507641198338287L;

    /**
     * 异常代码
     */
    protected Integer code;

    /**
     * 异常消息
     */
    protected String message;

    /**
     * 数据信息
     */
    protected Object data;

    public BusinessException() {
        super();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message + " : " + cause.getMessage();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.code = errorCode.getCode();
        this.message = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getDescription() + " : " + cause.getMessage();
    }

    public BusinessException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "errorCode: " + code + ", message: " + message;
    }
}
