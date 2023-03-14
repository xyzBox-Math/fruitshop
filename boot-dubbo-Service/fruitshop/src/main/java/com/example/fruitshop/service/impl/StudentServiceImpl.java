package com.example.fruitshop.service.impl;

import com.example.fruitshop.domain.Student;
import com.example.fruitshop.mapper.StudentMapper;
import com.example.fruitshop.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryAll() {
        return studentMapper.selectAll();
    }

    @Override
    public int removeStudentById(Integer id) {
        return studentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Student queryStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }


    @Override
    public int addStudentOne(Student st) {
        return studentMapper.insert(st);
    }

    @Override
    public List<Student> queryStudentListById(Integer[] id) {
        return studentMapper.selectAllByIdStudents(id);
    }
}
