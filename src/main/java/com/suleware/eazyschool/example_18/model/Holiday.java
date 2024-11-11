package com.suleware.eazyschool.example_18.model;

import lombok.Data;

@Data
public class Holiday {
  private String day;
  private String reason;
  private Type type;

  public Holiday(
      String day,
      String reason,
      Type type
  ) {
    this.day = day;
    this.reason = reason;
    this.type = type;
  }

  public enum Type {
    FESTIVAL, FEDERAL
  }
}
