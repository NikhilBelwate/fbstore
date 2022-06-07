package com.demo.fbstore.controller;

import com.demo.fbstore.model.Condition;
import com.demo.fbstore.model.Employee;
import com.demo.fbstore.service.EmployeeService;
import io.grpc.StatusRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/emp/list")
    public ResponseEntity<List<Employee>> getList(){
        List<Employee> empList=employeeService.getAllEmployees();
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }
    @GetMapping("/emp/get/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id){
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }
    @PostMapping("/emp/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return new ResponseEntity<>("{\"status\":\"saved\"}",HttpStatus.OK);
    }
    @DeleteMapping("/emp/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("{\"status\":\"deleted\"}",HttpStatus.OK);
    }
    @PutMapping("/emp/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>("{\"status\":\"updated\"}",HttpStatus.OK);
    }

    @Value("${query.error}")
    private String errorMsg;

    @PostMapping("/emp/find")
    public ResponseEntity<Object> findEmployee(@RequestHeader HttpHeaders headers, @RequestBody List<Condition> conditions){

        try{
            if(headers.get("token").contains("abc***52")) {
                List<Employee> empList = employeeService.fineEmployees(conditions);
                return new ResponseEntity<>(empList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Token mismatch",HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception se){
            return new ResponseEntity<>(errorMsg,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/emp/find")
    public ResponseEntity<Object> findEmployee(@RequestBody Condition condition) {
        List<Employee> empList = employeeService.findEmployees(condition);
        return new ResponseEntity<>(empList, HttpStatus.OK);
    }
    @GetMapping("/demo")
    public ResponseEntity<String> getDemo(){
        return new ResponseEntity<>("It is working",HttpStatus.OK);
    }
}
