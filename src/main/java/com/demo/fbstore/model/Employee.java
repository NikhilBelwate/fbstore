package com.demo.fbstore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class Employee {
    private Integer emp_id;
    private String document_id;
    private String name;
    private String roll;
    private Integer age;
    private List<String> education;


}
