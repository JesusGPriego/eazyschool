package com.suleware.eazyschool.example_18.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suleware.eazyschool.example_18.model.Holiday;
import com.suleware.eazyschool.example_18.repository.HolidaysRepository;

@Service
public class HolidayService {

  private HolidaysRepository holidaysRepository;

  public HolidayService(
      HolidaysRepository holidaysRepository
  ) {
    this.holidaysRepository = holidaysRepository;
  }

  public List<Holiday> getHolidays() {
    return (List<Holiday>) holidaysRepository.findAll();
  }
}
