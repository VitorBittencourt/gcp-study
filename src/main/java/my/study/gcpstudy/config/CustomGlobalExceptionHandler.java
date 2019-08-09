package my.study.gcpstudy.config;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
    final MethodArgumentNotValidException ex,
    final HttpHeaders headers,
    final HttpStatus status,
    final WebRequest request
  ) {

    final Map<String, Object> responseBody = new LinkedHashMap<>();
    responseBody.put("timestamp", LocalDateTime.now());
    responseBody.put("statusCode", status.value());
    responseBody.put("status", status.getReasonPhrase());

    final List<String> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(this::getFieldValidationErrorMessage)
      .sorted()
      .collect(Collectors.toList());

    responseBody.put("errors", errors);

    return new ResponseEntity<>(responseBody, headers, status);
  }

  private String getFieldValidationErrorMessage(final FieldError fieldError) {
    return String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage());
  }
}
