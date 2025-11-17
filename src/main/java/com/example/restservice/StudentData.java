package com.example.restservice;

import java.util.List;

public class StudentData {

  public List<Student> studentList;
  public boolean hasMore;

  public StudentData(List<Student> studentList, boolean hasMore) {
    this.studentList = studentList;
    this.hasMore = hasMore;
  }

}
