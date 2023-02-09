package com.example.datasources.service.impl;

import com.example.datasources.mapper.students.StudentMapper;
import com.example.datasources.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public int getStudentId() {
        return studentMapper.getStudent();
    }
}
