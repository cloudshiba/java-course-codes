package com.cloudshiba.core;

import com.cloudshiba.aop.ISchool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class School implements ISchool {
    @Autowired
    private Klass klass;

    @Override
    public void ding() {
        System.out.println("Class have " + this.klass.students.size() +" students.");
    }
}
