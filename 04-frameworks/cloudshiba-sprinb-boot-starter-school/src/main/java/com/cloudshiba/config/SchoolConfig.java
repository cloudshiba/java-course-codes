package com.cloudshiba.config;

import com.cloudshiba.core.Klass;
import com.cloudshiba.core.School;
import com.cloudshiba.core.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SchoolConfig {
    @Bean
    public List<Student> students() {
        List<Student> students = new ArrayList<>();

        Student student1 = Student.addNewOne(1, "Wee");
        Student student2 = Student.addNewOne(2, "Fee");
        students.add(student1);
        students.add(student2);

        return students;
    }

    @Bean
    public Klass klass() {
        return new Klass();
    }

    @Bean
//    @ConditionalOnMissingBean(School.class)
    public School school() {
        return new School();
    }
}
