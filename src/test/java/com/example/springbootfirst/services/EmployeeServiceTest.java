package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    @Mock
    RegisterDetailsRepository registerDetailsRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1 = new RegisterDetails();
        RegisterDetails emp2 = new RegisterDetails();
        when(registerDetailsRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeService.getMethod();
        System.out.println(result);
        assertEquals(2,result.size());
    }

    @Test
    void testGetById() {
        int empId = 1;
        RegisterDetails emp = new RegisterDetails();
        emp.setEmpId(empId);
        when(registerDetailsRepository.findById(empId)).thenReturn(Optional.of(emp));
        RegisterDetails result = employeeService.getEmployeeById(empId);
        assertEquals(empId, result.getEmpId());
    }

    @Test
    void testUpdateEmployee() {
        int empId = 1;
        RegisterDetails existing = new RegisterDetails();
        existing.setEmpId(empId);
        when(registerDetailsRepository.findById(empId)).thenReturn(Optional.of(existing));
        when(registerDetailsRepository.save(existing)).thenReturn(existing);
        String result = employeeService.updateEmployee(empId);
        assertEquals("Employee Updated Successfully", result);
    }

    @Test
    void testDeleteEmployeeById() {
        int empId = 1;
        doNothing().when(registerDetailsRepository).deleteById(empId);
        String result = employeeService.deleteEmployeeById(empId);
        assertEquals("Employee Deleted Successfully", result);

    }
}