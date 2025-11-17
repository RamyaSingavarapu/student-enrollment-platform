package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentDataController {

  @Autowired
  private StudentRepository studentRepository;

  @GetMapping("/students")
  public StudentData getStudentData(@RequestParam int pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, 5);
    Page<Student> studentsPage = studentRepository.findAll(pageable);
    boolean hasMore = studentsPage.hasNext();
    List<Student> studentList = studentsPage.toList();
    return new StudentData(studentList,hasMore);
  }
}
