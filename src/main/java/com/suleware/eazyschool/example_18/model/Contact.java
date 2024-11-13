package com.suleware.eazyschool.example_18.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Getter
@Setter
@Entity
@Table(name = "contact_msg")
public class Contact extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "contact_id")
  private Long contactId;

  @NotBlank(message = "Name must not be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  private String name;

  @NotBlank(message = "Mobile number must not be blank")
  @Pattern(regexp = "(^$|[\\d]{10})",
      message = "Mobile number must be 10 digits")
  private String mobileNum;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Email must be valid")
  private String email;

  @NotBlank(message = "Subject must not be blank")
  private String subject;

  @NotBlank(message = "Message must not be blank")
  private String message;

  private String status;

}