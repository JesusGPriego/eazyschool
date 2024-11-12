package com.suleware.eazyschool.example_18.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.suleware.eazyschool.example_18.model.Holiday;
import com.suleware.eazyschool.example_18.service.HolidayService;

@Controller
public class HolidayController {

  private HolidayService holidayService;

  public HolidayController(
      HolidayService holidayService
  ) {
    this.holidayService = holidayService;
  }

  private String displayHolidays(
      @RequestParam(required = false)
      Optional<Boolean> festival,
      @RequestParam(required = false)
      Optional<Boolean> federal,
      Model model
  ) {

    model.addAttribute("festival", festival.orElse(false));
    model.addAttribute("federal", federal.orElse(false));
    List<Holiday> holidays = holidayService.getHolidays();
    Holiday.Type[] types = Holiday.Type.values();
    for (Holiday.Type type : types) {
      model.addAttribute(
          type.toString(),
          (holidays.stream()
              .filter(holiday -> holiday.getType().equals(type))
              .collect(Collectors.toList()))
      );
    }
    return "holidays.html";
  }

  @GetMapping("/holidays/{display}")
  public String displayHolidaysViaPath(
      @PathVariable
      String display,
      Model model
  ) {

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

    return displayHolidays(festival, federal, model);
  }

}
