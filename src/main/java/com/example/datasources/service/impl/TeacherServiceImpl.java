package com.example.datasources.service.impl;

import com.example.datasources.mapper.teachers.TeacherMapper;
import com.example.datasources.model.Teacher;
import com.example.datasources.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacher() {
        return teacherMapper.getTeacher();
    }
}
