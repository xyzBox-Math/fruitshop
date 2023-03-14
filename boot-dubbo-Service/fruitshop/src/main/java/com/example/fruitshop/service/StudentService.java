package com.example.fruitshop.service;

import com.example.fruitshop.domain.Student;

import java.util.List;

public interface StudentService {
    Student queryStudentById(Integer id);

    List<Student> queryAll();

    int removeStudentById(Integer id);

    int addStudentOne(Student st);

    List<Student> queryStudentListById(Integer[]id);
}
