package com.suleware.eazyschool.example_18.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
  @RequestMapping(value = "/login", method = {
      RequestMethod.GET, RequestMethod.POST
  })
  public String displayLoginPage(
      @RequestParam(required = false)
      String error,
      @RequestParam(required = false)
      String logout,
      @RequestParam(required = false)
      String register,
      Model model
  ) {
    String errorMessage = null;
    if (error != null) {
      errorMessage = "Username or Password is incorrect !!";
    } else if (logout != null) {
      errorMessage = "You have been successfully logged out !!";
    } else if (register != null) {
      errorMessage =
          "You registration successful. Login with registered credentials !!";
    }

    model.addAttribute("errorMessage", errorMessage);
    return "login.html";
  }

  @GetMapping(value = "/logout")
  public String logoutPage(
      HttpServletRequest request,
      HttpServletResponse response
  ) {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null) {
      new SecurityContextLogoutHandler()
          .logout(request, response, authentication);
    }
    return "redirect:/login?logout=true";
  }

}