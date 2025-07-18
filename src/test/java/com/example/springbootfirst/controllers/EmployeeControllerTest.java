package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {
  @Mock
    EmployeeService employeeService;

  @InjectMocks
    EmployeeController employeeController;

  @BeforeEach
  void setUp(){
      MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRoute(){
      String result = employeeController.route();
      assertEquals("Welcome to SpringBoot Security",result);
  }

  @Test
  void testGetMethod(){
      RegisterDetails emp1 = new RegisterDetails();
      RegisterDetails emp2 = new RegisterDetails();
      when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
      List<RegisterDetails> result = employeeController.getMethod();
      System.out.println(result);
      assertEquals(2,result.size());
  }

    @Test
    void testGetEmployeeById() {
        int empId = 1;
        RegisterDetails mockEmployee = new RegisterDetails();
        when(employeeService.getEmployeeById(empId)).thenReturn(mockEmployee);
        RegisterDetails result = employeeController.getEmployeeById(empId);
        assertEquals(mockEmployee, result);
    }

    @Test
    void testPostMethod() {
        UserDetailsDto newEmployee = new UserDetailsDto();
        when(employeeService.addNewEmployee(newEmployee)).thenReturn("Employee added");
        String result = employeeController.postMethod(newEmployee);
        assertEquals("Employee added", result);
    }

//    @Test
//    void testPutMethod() {
//        int empId = 1;
//        when(employeeService.updateEmployee(empId)).thenReturn("Employee updated");
//        String result = employeeController.putMethod(empId);
//        assertEquals("Employee updated", result);
//    }

    @Test
    void testDeleteMethod() {
        int empId = 1;
        when(employeeService.deleteEmployeeById(empId)).thenReturn("Employee deleted");
        String result = employeeController.deleteMethod(empId);
        assertEquals("Employee deleted", result);
    }
}