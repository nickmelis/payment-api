package com.cloudfle.payment.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize (builder = ResponseEnvelope.Builder.class)
@JsonInclude (Include.NON_EMPTY)
public class ResponseEnvelope {

  private final Object data;
  private final List<String> errors;
  private final Map<String, String> links;

  private ResponseEnvelope(
      Builder builder) {
    this.data = builder.data;
    this.errors = builder.errors;
    this.links = builder.links;
  }

  public Object getData() {
    return data;
  }

  public List<String> getErrors() {
    return errors;
  }

  public Map<String, String> getLinks() {
    return links;
  }

  /**
   * Creates builder to build {@link ResponseEnvelope}.
   *
   * @return created builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder to build {@link ResponseEnvelope}.
   */
  public static final class Builder {
    private Object data;
    private List<String> errors = new ArrayList<>();
    private Map<String, String> links = new HashMap<>();

    private Builder() {
    }

    public Builder withData(Object data) {
      this.data = data;
      return this;
    }

    public Builder withErrors(List<String> errors) {
      this.errors = errors;
      return this;
    }

    public Builder withError(String error) {
      this.errors.add(error);
      return this;
    }

    public Builder withLinks(Map<String, String> links) {
      this.links = links;
      return this;
    }

    public Builder withLink(String key, String value) {
      this.links.put(key, value);
      return this;
    }

    public ResponseEnvelope build() {
      return new ResponseEnvelope(this);
    }
  }
}
