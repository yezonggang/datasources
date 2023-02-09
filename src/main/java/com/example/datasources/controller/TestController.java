package com.example.datasources.controller;

import com.example.datasources.model.Teacher;
import com.example.datasources.service.StudentService;
import com.example.datasources.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;


    @GetMapping("/test")
    public void test(){
        int studentId = studentService.getStudentId();
        Teacher teacher = teacherService.getTeacher();
        log.info(String.format("------->%s,%s",studentId,teacher.getId()));
    }
}
