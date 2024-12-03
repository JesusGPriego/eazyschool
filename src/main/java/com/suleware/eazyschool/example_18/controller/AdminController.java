package com.suleware.eazyschool.example_18.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.suleware.eazyschool.example_18.model.Course;
import com.suleware.eazyschool.example_18.model.EazyClass;
import com.suleware.eazyschool.example_18.model.Person;
import com.suleware.eazyschool.example_18.repository.CourseRepository;
import com.suleware.eazyschool.example_18.repository.EazyClassRepository;
import com.suleware.eazyschool.example_18.repository.PersonRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

  private EazyClassRepository eazyClassRepository;
  private PersonRepository personRepository;
  private CourseRepository courseRepository;

  public AdminController(
      EazyClassRepository eazyClassRepository,
      PersonRepository personRepository,
      CourseRepository courseRepository
  ) {
    this.eazyClassRepository = eazyClassRepository;
    this.personRepository = personRepository;
    this.courseRepository = courseRepository;
  }

  @GetMapping("/displayClasses")
  public ModelAndView displayClasses(
      Model model
  ) {
    List<EazyClass> eazyClasses = eazyClassRepository.findAll();
    ModelAndView modelAndView = new ModelAndView("classes.html");
    modelAndView.addObject("eazyClasses", eazyClasses);
    modelAndView.addObject("eazyClass", new EazyClass());
    return modelAndView;
  }

  @PostMapping("/addNewClass")
  public ModelAndView addNewClass(
      Model model,
      @ModelAttribute
      EazyClass eazyClass
  ) {
    eazyClassRepository.save(eazyClass);
    ModelAndView modelAndView =
        new ModelAndView("redirect:/admin/displayClasses");
    return modelAndView;
  }

  @GetMapping("/deleteClass")
  public ModelAndView deleteClass(
      Model model,
      @RequestParam
      Long id
  ) {
    Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
    for (Person person : eazyClass.get().getPersons()) {
      person.setEazyClass(null);
      personRepository.save(person);
    }
    eazyClassRepository.deleteById(id);
    ModelAndView modelAndView =
        new ModelAndView("redirect:/admin/displayClasses");
    return modelAndView;
  }

  @GetMapping("/displayStudents")
  public ModelAndView displayStudents(
      Model model,
      @RequestParam
      Long classId,
      HttpSession session,
      @RequestParam(required = false)
      String error
  ) {
    String errorMessage = null;
    ModelAndView modelAndView = new ModelAndView("students.html");
    Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
    modelAndView.addObject("eazyClass", eazyClass.get());
    modelAndView.addObject("person", new Person());
    session.setAttribute("eazyClass", eazyClass.get());
    if (error != null) {
      errorMessage = "Invalid Email entered!!";
      modelAndView.addObject("errorMessage", errorMessage);
    }
    return modelAndView;
  }

  @PostMapping("/addStudent")
  public ModelAndView addStudent(
      Model model,
      @ModelAttribute
      Person person,
      HttpSession session
  ) {
    ModelAndView modelAndView = new ModelAndView();
    EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
    Person personEntity = personRepository.findByEmail(person.getEmail());
    if (personEntity == null || !(personEntity.getPersonId() > 0)) {
      modelAndView.setViewName(
          "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId()
              + "&error=true"
      );
      return modelAndView;
    }
    personEntity.setEazyClass(eazyClass);
    personRepository.save(personEntity);
    eazyClass.getPersons().add(personEntity);
    eazyClassRepository.save(eazyClass);
    modelAndView.setViewName(
        "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId()
    );
    return modelAndView;
  }

  @GetMapping("/deleteStudent")
  public ModelAndView deleteStudent(
      Model model,
      @RequestParam
      Long personId,
      HttpSession session
  ) {
    EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
    Optional<Person> person = personRepository.findById(personId);
    person.get().setEazyClass(null);
    eazyClass.getPersons().remove(person.get());
    EazyClass eazyClassSaved = eazyClassRepository.save(eazyClass);
    session.setAttribute("eazyClass", eazyClassSaved);
    ModelAndView modelAndView = new ModelAndView(
        "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId()
    );
    return modelAndView;
  }

  @GetMapping("/displayCourses")
  public ModelAndView displayCourses(
      Model model
  ) {

    // List<Course> courses = courseRepository.findByOrderByNameDesc();
    List<Course> courses =
        courseRepository.findAll(Sort.by("name").ascending());
    ModelAndView modelAndView = new ModelAndView("courses_secure.html");
    modelAndView.addObject("courses", courses);
    modelAndView.addObject("course", new Course());
    return modelAndView;
  }

  @PostMapping("/addNewCourse")
  public ModelAndView addNewCourse(
      Model model,
      Course course
  ) {
    ModelAndView modelAndView = new ModelAndView();
    courseRepository.save(course);
    modelAndView.setViewName("redirect:/admin/displayCourses");
    return modelAndView;
  }

  @GetMapping("/viewStudents")
  public ModelAndView viewStudents(
      Model model,
      @RequestParam
      Long id,
      HttpSession session,
      @RequestParam(required = false)
      String error
  ) {
    String errorMessage = null;
    ModelAndView modelAndView = new ModelAndView("course_students.html");
    Optional<Course> courses = courseRepository.findById(id);
    modelAndView.addObject("courses", courses.get());
    modelAndView.addObject("person", new Person());
    session.setAttribute("courses", courses.get());
    if (error != null) {
      errorMessage = "Invalid Email entered!!";
      modelAndView.addObject("errorMessage", errorMessage);
    }
    return modelAndView;
  }

  @PostMapping("/addStudentToCourse")
  public ModelAndView addStudentToCourse(
      Model model,
      Person person,
      HttpSession session
  ) {
    ModelAndView modelAndView = new ModelAndView();
    Course course = (Course) session.getAttribute("courses");
    Person personEntity = personRepository.findByEmail(person.getEmail());
    if (personEntity == null || !(personEntity.getPersonId() > 0)) {
      modelAndView.setViewName(
          "redirect:/admin/viewStudents?id=" + course.getCourseId()
              + "&error=true"
      );
      return modelAndView;
    }
    personEntity.getCourses().add(course);
    course.getPersons().add(personEntity);
    personRepository.save(personEntity);
    session.setAttribute("courses", course);
    modelAndView
        .setViewName("redirect:/admin/viewStudents?id=" + course.getCourseId());
    return modelAndView;
  }

  @GetMapping("/deleteStudentFromCourse")
  public ModelAndView deleteStudentFromCourse(
      Model model,
      @RequestParam
      Long personId,
      HttpSession session
  ) {
    Course courses = (Course) session.getAttribute("courses");
    Optional<Person> person = personRepository.findById(personId);
    person.get().getCourses().remove(courses);
    courses.getPersons().remove(person);
    personRepository.save(person.get());
    session.setAttribute("courses", courses);
    ModelAndView modelAndView = new ModelAndView(
        "redirect:/admin/viewStudents?id=" + courses.getCourseId()
    );
    return modelAndView;
  }

}
