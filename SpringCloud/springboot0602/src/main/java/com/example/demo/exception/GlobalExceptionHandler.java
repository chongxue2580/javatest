package com.example.demo.exception;

import com.example.demo.common.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理实体未找到异常
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Result<Void>> handleEntityNotFound(EntityNotFoundException ex) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(Result.error(404, ex.getMessage()));
  }

  /**
   * 处理参数校验失败异常
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Result<Map<String, String>>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Result.error(400, "参数验证失败", errors));
  }

  /**
   * 处理非法参数异常
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Result<Void>> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Result.error(400, ex.getMessage()));
  }

  /**
   * 处理其他未预期的异常
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result<Void>> handleOtherExceptions(Exception ex) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Result.error(500, "服务器内部错误"));
  }
}