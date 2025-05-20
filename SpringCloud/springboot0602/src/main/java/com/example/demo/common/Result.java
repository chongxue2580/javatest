package com.example.demo.common;

import lombok.Data;

/**
 * 统一API响应结果封装
 * 
 * @param <T> 响应数据的类型
 */
@Data
public class Result<T> {
  /**
   * 状态码
   */
  private Integer code;

  /**
   * 响应信息
   */
  private String message;

  /**
   * 响应数据
   */
  private T data;

  /**
   * 时间戳
   */
  private long timestamp;

  /**
   * 成功响应
   * 
   * @param data 响应数据
   */
  public static <T> Result<T> success(T data) {
    Result<T> result = new Result<>();
    result.setCode(200);
    result.setMessage("操作成功");
    result.setData(data);
    result.setTimestamp(System.currentTimeMillis());
    return result;
  }

  /**
   * 失败响应
   * 
   * @param code    错误码
   * @param message 错误信息
   */
  public static <T> Result<T> error(int code, String message) {
    Result<T> result = new Result<>();
    result.setCode(code);
    result.setMessage(message);
    result.setTimestamp(System.currentTimeMillis());
    return result;
  }

  /**
   * 失败响应（带数据）
   * 
   * @param code    错误码
   * @param message 错误信息
   * @param data    错误数据
   */
  public static <T> Result<T> error(int code, String message, T data) {
    Result<T> result = new Result<>();
    result.setCode(code);
    result.setMessage(message);
    result.setData(data);
    result.setTimestamp(System.currentTimeMillis());
    return result;
  }
}