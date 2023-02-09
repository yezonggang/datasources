package com.example.datasources.mapper.teachers;

import com.example.datasources.model.Teacher;
import org.apache.ibatis.annotations.Mapper;

public interface TeacherMapper {
    Teacher getTeacher();

}
