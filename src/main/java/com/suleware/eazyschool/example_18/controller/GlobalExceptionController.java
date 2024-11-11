package com.suleware.eazyschool.example_18.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(Exception.class)
  public ModelAndView handleException(Exception e) {
    ModelAndView errorPage = new ModelAndView();
    errorPage.setViewName("error.html");
    errorPage.addObject("errorMsg" , e.getMessage());
    return errorPage;
  }
}
