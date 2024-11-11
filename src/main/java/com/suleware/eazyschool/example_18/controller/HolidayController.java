package com.suleware.eazyschool.example_18.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.suleware.eazyschool.example_18.model.Holiday;

@Controller
public class HolidayController {

  private String displayHolidays(
      @RequestParam(required = false) Optional<Boolean> festival,
      @RequestParam(required = false) Optional<Boolean> federal, Model model) {

    model.addAttribute("festival" , festival.orElse(false));
    model.addAttribute("federal" , federal.orElse(false));
    List<Holiday> holidays = Arrays.asList(
        new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL) ,
        new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL) ,
        new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL) ,
        new Holiday(" Dec 25 ", "Christmas", Holiday.Type.FESTIVAL) ,
        new Holiday(" Jan 17 ", "Martin Luther King Jr. Day",
            Holiday.Type.FEDERAL) ,
        new Holiday(" July 4 ", "Independence Day", Holiday.Type.FEDERAL) ,
        new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL) ,
        new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL));
    Holiday.Type[] types = Holiday.Type.values();
    for (Holiday.Type type : types) {
      model.addAttribute(type.toString() ,
          (holidays.stream()
              .filter(holiday -> holiday.type().equals(type))
              .collect(Collectors.toList())));
    }
    return "holidays.html";
  }

  @GetMapping("/holidays/{display}")
  public String displayHolidaysViaPath(@PathVariable String display,
      Model model) {

    Optional<Boolean> festival = Optional.empty();
    Optional<Boolean> federal = Optional.empty();

    if (null != display && display.equals("all")) {
      festival = Optional.of(true);
      federal = Optional.of(true);
    } else if (null != display && display.equals("federal")) {
      federal = Optional.of(true);
    } else if (null != display && display.equals("festival")) {
      festival = Optional.of(true);
    }

    return displayHolidays(festival , federal , model);
  }

}
