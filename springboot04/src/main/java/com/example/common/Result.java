package com.example.common;

import lombok.Data;

@Data
public class Result<T> {
  private Integer code;
  private String message;
  private T data;

  public static <T> Result<T> success() {
    return success(null);
  }

  public static <T> Result<T> success(T data) {
    Result<T> result = new Result<>();
    result.setCode(200);
    result.setData(data);
    result.setMessage("操作成功");
    return result;
  }

  public static <T> Result<T> success(T data, String message) {
    Result<T> result = new Result<>();
    result.setCode(200);
    result.setData(data);
    result.setMessage(message);
    return result;
  }

  public static <T> Result<T> error(String message) {
    Result<T> result = new Result<>();
    result.setCode(500);
    result.setMessage(message);
    return result;
  }

  public static <T> Result<T> error(String message, T data) {
    Result<T> result = new Result<>();
    result.setCode(500);
    result.setMessage(message);
    result.setData(data);
    return result;
  }
}