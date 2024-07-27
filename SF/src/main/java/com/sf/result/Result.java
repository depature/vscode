package com.sf.result;


import com.sf.constant.StatusCode;
import java.io.Serializable;
import java.util.HashMap;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**.
 * 后端统一返回结果
 *
 */
@Data
public class Result<T> implements Serializable {

  private Code state;
  private T data; //数据

  public Result() {
    state = new Code();
  }

  /**.
   * 返回正确码
   */
  @NotNull
  public static <T> Result<T> success(T object, String msg) {
    Result<T> result = new Result<T>();

    result.data = object;
    result.state.code = StatusCode.SUCCESS;
    result.state.message = msg;
    return result;
  }

  /**.
   * 返回错误码
   */
  @NotNull
  public static <T> Result<T> error(Integer code) {
    Result result = new Result();
    result.state.message = StatusCode.getMessage(code);
    result.state.code = code;
    result.data = new HashMap<>();
    return result;
  }

  @Data
  private static class Code implements Serializable {
    private Integer code;
    private String message;
  }

}
