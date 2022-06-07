package com.demo.fbstore.service;

import com.demo.fbstore.model.Condition;
import com.demo.fbstore.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer ID);
    void addEmployee(Employee employee);
    boolean deleteEmployee(Integer ID);
    void updateEmployee(Employee employee);

    List<Employee> fineEmployees(List<Condition> conditions);

    List<Employee> findEmployees(Condition condition);

}
