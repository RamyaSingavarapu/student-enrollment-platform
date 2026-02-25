package com.example.restservice;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class StudentController {

    @Autowired // Springboot creates an class that implements StudentRepository interface, instantiates it and assigns its reference to the field below
    private StudentRepository studentRepository;

    // public StudentController(StudentRepository studentRepository) {
    //     this.studentRepository = studentRepository;
    // }
    @GetMapping("/create-student")
    public String createStudent(Model model) {
        Student student = new Student("", "", "", "");

        model.addAttribute("student", student);

        return "create-student";
    }

    @PostMapping("create-student")
    public String createStudent(@ModelAttribute Student student) {//@ModelAttribute we are asking springboot to create an object with the details(student i.e as keyvalue pairs with &) that came from browser and save reference in a variable(student) 
        Student savedStudent = studentRepository.save(student);

        return "redirect:/student/" + savedStudent.getId();
    }

    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable String id, Model model) {//@PathVariable means we are requesting springboot to pass the id that is in the path to this method
        Optional<Student> mayBeStudent = studentRepository.findById(id);

        // if (mayBeStudent.isPresent()) {
        //     Student student = mayBeStudent.get();
        // }
        mayBeStudent.ifPresent(student -> model.addAttribute("student", student));

        Boolean noStudentPresent = mayBeStudent.isEmpty();

        model.addAttribute("noStudentPresent", noStudentPresent);

        return "student-details";
    }

    @GetMapping("/student-list")
    public String displayStudentList(Model model) {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Student> studentsPage = studentRepository.findAll(pageable);
        List<Student> students = studentsPage.toList();
        model.addAttribute("students", students);
        return "student-list";
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudentById(@PathVariable String id, Model model) {
        Optional<Student> mayBeStudent = studentRepository.findById(id);
        mayBeStudent.ifPresent(student -> model.addAttribute("student", student));
        mayBeStudent.ifPresent(student -> studentRepository.delete(student));
        Boolean studentPresent = mayBeStudent.isPresent();
        model.addAttribute("studentPresent", studentPresent);
        return "delete-student";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Optional<Student> mayBeStudent = studentRepository.findById(id);
        mayBeStudent.ifPresent(student -> model.addAttribute("student", student));
        return "edit-student";
    }

    @PutMapping("/students/{id}")
    public String updateStudentById(@PathVariable String id,
            @ModelAttribute Student student) {
        studentRepository.save(student);

        return "redirect:/student-list";
    }

}
