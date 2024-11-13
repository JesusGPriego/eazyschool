package com.suleware.eazyschool.example_18.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {
  @Id
  private String day;
  private String reason;
  @Enumerated(EnumType.STRING)
  private Type type;

  public Holiday() {
  }

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
