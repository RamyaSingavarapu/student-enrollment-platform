package com.example.restservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students") //@Document means this is a document that must be saved in mongodb
public class Student { //it is an entity/model class

    @Id
    private String id;

    private final String firstName;

    private final String lastName;

    private final String rollNumber;

    private final String department;

    public Student(String firstName, String lastName, String rollNumber, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rollNumber = rollNumber;
        this.department = department;
    }

    public String getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getRollNumber() {
        return this.rollNumber;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setId(String id) {
        this.id = id;
    }
}
