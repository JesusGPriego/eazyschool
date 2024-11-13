package com.suleware.eazyschool.example_18.model;

import com.suleware.eazyschool.example_18.annotation.FieldsValueMatch;
import com.suleware.eazyschool.example_18.annotation.PasswordValidator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
    @FieldsValueMatch(field = "pwd",
        fieldMatch = "confirmPwd",
        message = "Passwords does not match"),
    @FieldsValueMatch(field = "email",
        fieldMatch = "confirmEmail",
        message = "Emails does not match")
})
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Name must not be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  private String name;

  @NotBlank(message = "Mobile number must not be blank")
  @Pattern(regexp = "(^$|[\\d]{10})",
      message = "Mobile number must be 10 digits")
  private String mobileNumber;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Please provide a valid email address")
  private String email;

  @NotBlank(message = "Confirm Email must not be blank")
  @Email(message = "Please provide a valid confirm email address")
  @Transient
  private String confirmEmail;

  @NotBlank(message = "Password must not be blank")
  @Size(min = 5, message = "Password must be at least 5 characters long")
  @PasswordValidator
  private String pwd;

  @NotBlank(message = "Confirm Password must not be blank")
  @Size(min = 5,
      message = "Confirm Password must be at least 5 characters long")
  @Transient
  private String confirmPwd;
}
