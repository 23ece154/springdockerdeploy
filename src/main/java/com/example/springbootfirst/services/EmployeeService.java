//package com.example.springbootfirst.services;
//
//import com.example.springbootfirst.models.Employee;
//import com.example.springbootfirst.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//    @Autowired
//    EmployeeRepository empRepo;
//
//    public List<Employee> getMethod() {
//        return empRepo.findAll();
//    }
//
//    public Employee getEmployeeById(int empID) {
//        return empRepo.findById(empID).orElse(new Employee());
//    }
//
//
//    public List<Employee> getEmployeeByJob(String job) {
//        return empRepo.findByJob(job);
//    }
//
//    public String addEmployee(Employee employee) {
//        empRepo.save(employee);
//        return "Employee Added Successfully!!!";
//    }
//
//    public String updateEmployee(Employee employee) {
//        empRepo.save(employee);
//        return "Employee Updated Successfully!!!";
//    }
//
//    public String deleteEmployeeById(int empID) {
//        empRepo.deleteById(empID);
//        return "Employee Deleted Successfully!!!";
//    }
//
//}


package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    public List<RegisterDetails> getMethod() {
        return registerDetailsRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empId) {
        return registerDetailsRepository.findById(empId).orElse(new RegisterDetails());
    }

//    public List<RegisterDetails> getEmployeeByJob() {
//        return registerDetailsRepository.findByRole();
//    }

    public String updateEmployee(int empId) {
        RegisterDetails user = registerDetailsRepository.findById(empId)
                .orElseThrow(()->new RuntimeException("No Such User Present"));
        registerDetailsRepository.save(user);
        return "Employee Updated Successfully";
    }

    public String deleteEmployeeById(int empID) {
        registerDetailsRepository.deleteById(empID);
        return "Employee Deleted Successfully";
    }

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName: register.getRoleNames()){
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        System.out.println("Registration"+ registerDetails);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }
}