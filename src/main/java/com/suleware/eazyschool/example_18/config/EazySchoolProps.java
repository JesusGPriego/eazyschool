package com.suleware.eazyschool.example_18.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Component("eazySchoolProps")
@Data
@ConfigurationProperties(prefix = "eazyschool")
@Validated
public class EazySchoolProps {
  @Min(value = 5, message = "Page size must be between 5 and 25")
  @Max(value = 25, message = "Page size must be between 5 and 25")
  private int pageSize;

  private Map<String, String> contact;

  private List<String> branches;
}
