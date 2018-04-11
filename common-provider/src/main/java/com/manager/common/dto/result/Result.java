package com.manager.common.dto.result;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Result<T> implements Serializable {

    private static final long serialVersionUID = 64655331555509136L;

    public static final Result FAIL = Result.newResult(Code.FAIL);
    public static final Result SUCCESS = Result.newResult(Code.SUCCESS);

    /**
     * @see Code#code()
     */
    private Integer code;

    /**
     * @see Code#msg()
     */
    private String msg;

    /**
     * data
     */
    private T data;

    /**
     * ext
     */
    private Object ext;

    public Result() {
    }

    public static <T> Result<T> newResult(Code code, T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code.code());
        tResult.setMsg(code.msg());
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result<T> newResult(int code, String msg) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    public static <T> Result<T> newResult(Code code, T data, Object ext) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code.code());
        tResult.setMsg(code.msg());
        tResult.setData(data);
        tResult.setExt(ext);
        return tResult;
    }

    public static <T> Result<T> newResult(Code code) {
        return newResult(code, null);
    }

    public static <T> Result<T> newSuccessResult(T data) {
        return newResult(Code.SUCCESS, data);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
