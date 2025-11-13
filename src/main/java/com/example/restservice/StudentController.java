package com.example.restservice;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/create-student")
    public String createStudent(Model model) {
        Student student = new Student("", "", "", "");

        model.addAttribute("student", student);

        return "create-student";
    }

    @PostMapping("create-student")
    public String createStudent(@ModelAttribute Student student, Model model) {
        Student savedStudent = studentRepository.save(student);

        return "redirect:/student/"+savedStudent.getId();
    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable String id, Model model){
        Optional<Student> mayBeStudent = studentRepository.findById(id);

        mayBeStudent.ifPresent(student -> model.addAttribute("student", student));

        Boolean noStudentPresent = mayBeStudent.isEmpty();

        model.addAttribute("noStudentPresent", noStudentPresent );

        return "student-details";
    }

    @GetMapping("/student-list")
    public String displayStudentList(Model model){
      List<Student> students = studentRepository.findAll();
      model.addAttribute("students", students);
      return "student-list";
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable String id, Model model){
       Optional<Student> mayBeStudent = studentRepository.findById(id);
        mayBeStudent.ifPresent(student -> model.addAttribute("student", student));
        mayBeStudent.ifPresent(student-> studentRepository.delete(student));
        Boolean noStudentPresent = mayBeStudent.isEmpty();
        model.addAttribute("noStudentPresent", noStudentPresent);
        return "delete-student";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model){
        Optional<Student> mayBeStudent = studentRepository.findById(id);
        mayBeStudent.ifPresent(student->model.addAttribute("student", student));
        return "edit-student";
    }

    @PutMapping("/students/{id}")
    public String updateStudentById(@PathVariable String id,
                                    @ModelAttribute Student student){
       studentRepository.save(student);

        return "redirect:/student-list";
    }


}
