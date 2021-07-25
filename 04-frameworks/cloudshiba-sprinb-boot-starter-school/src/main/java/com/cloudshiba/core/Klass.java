package com.cloudshiba.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Klass {
    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }
}