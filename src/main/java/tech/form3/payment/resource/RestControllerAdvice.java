package tech.form3.payment.resource;

import java.util.List;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import tech.form3.payment.model.ResponseEnvelope;

@ControllerAdvice
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {

  /**
   * Selects which types are being adviced (wrapped)
   */
  @Override
  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
      ServerHttpResponse response) {
    // don't wrap a body that is already wrapped (must be exception being handled)
    if (body instanceof ResponseEnvelope) {
      return body;
    }

    return getResponseEnvelope(body);
  }

  public static ResponseEnvelope getResponseEnvelope(Object data) {
    return ResponseEnvelope.builder()
      .withData(data)
      .withLink("self", "https://api.test.form3.tech/v1/payments")
      .build();
  }

  public static ResponseEnvelope getErrorResponseEnvelope(String error) {
    return ResponseEnvelope.builder()
      .withError(error)
      .withLink("self", "https://api.test.form3.tech/v1/payments")
      .build();
  }

  public static ResponseEnvelope getErrorResponseEnvelope(List<String> errors) {
    return ResponseEnvelope.builder()
      .withErrors(errors)
      .withLink("self", "https://api.test.form3.tech/v1/payments")
      .build();
  }
}
