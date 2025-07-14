//package com.example.springbootfirst.controllers;
//
//
//import com.example.springbootfirst.models.Employee;
//import com.example.springbootfirst.services.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/")
//    public String route(){
//        return "Welcome to SpringBoot Security";
//    }
//
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/employee")
//    public List<Employee> getMethod(){
//        return employeeService.getMethod();
//    }
//
//    @GetMapping("/employee/{empID}")
//    public Employee getEmployeeById(@PathVariable int empID){
//        return employeeService.getEmployeeById(empID);
//    }
//
//    @GetMapping("/employee/job/{job}")
//    public List<Employee> getEmployeeByJob(@PathVariable String job){
//        return employeeService.getEmployeeByJob(job);
//    }
//
//    @PostMapping("/employee")
//    public String postMethod(@RequestBody Employee employee){
////        Employee employee = new Employee(5,"Sivagami", "Business");
//        return employeeService.addEmployee(employee);
//    }
//    @PutMapping("/employee")
//    public String putMethod(@RequestBody Employee employee){
//        return employeeService.updateEmployee(employee);
//    }
//    @DeleteMapping("/employee/{empID}")
//    public String deleteMethod(@PathVariable int empID){
//        return employeeService.deleteEmployeeById(empID);
//    }
//
//
//}

package com.example.springbootfirst.controllers;


import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/")
    public String route(){
        return "Welcome to SpringBoot Security";
    }


    @GetMapping("/employee")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<RegisterDetails> getMethod(){
        return employeeService.getMethod();
    }


    @GetMapping("/employee/{empId}")
    @PreAuthorize("hasAnyRole('USER')")
    public RegisterDetails getEmployeeById(@PathVariable int empId){
        System.out.println();
        return employeeService.getEmployeeById(empId);
    }



//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @GetMapping("/employee/job/{job}")
//    public List<RegisterDetails> getEmployeeByJob(@PathVariable String job){
//        return employeeService.getEmployeeByJob(job);
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employee")
    public String postMethod(@RequestBody UserDetailsDto employee){
//        Employee employee = new Employee(5,"Sivagami", "Business");
        return employeeService.addNewEmployee(employee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employee/{empId}")
    public String putMethod(@PathVariable int empId){
        return employeeService.updateEmployee(empId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employee/{empID}")
    public String deleteMethod(@PathVariable int empID){
        return employeeService.deleteEmployeeById(empID);
    }
}