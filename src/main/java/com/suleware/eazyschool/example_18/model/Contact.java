package com.suleware.eazyschool.example_18.model;


public record Contact(
    String name,
    String mobileNum,
    String email,
    String subject,
    String message
) {}