package org.sfx.result;

import lombok.Data;
import org.sfx.constant.StatusCode;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Code state;
    private T data; //数据

    public Result() {
        state=new Code();
    }

    public static <T> Result<T> success(T object, String msg) {
        Result<T> result = new Result<T>();

        result.data = object;
        result.state.code = StatusCode.SUCCESS;
        result.state.Message=msg;
        return result;
    }

    public static <T> Result<T> error(Integer code,String msg) {
        Result result = new Result();
        result.state.Message = msg;
        result.state.code = code;
        return result;
    }
    @Data
    private static class Code implements Serializable{
        private Integer code;
        private String Message;
    }

}
