


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

//    public String updateEmployee(int empId) {
//        RegisterDetails user = registerDetailsRepository.findById(empId)
//                .orElseThrow(()->new RuntimeException("No Such User Present"));
//        registerDetailsRepository.save(user);
//        return "Employee Updated Successfully";
//    }

    public String updateEmployee(int empId, UserDetailsDto updatedData) {
        RegisterDetails employee = registerDetailsRepository.findById(empId).orElse(null);
        if (employee != null) {
            if (updatedData.getName() != null) employee.setName(updatedData.getName());
            if (updatedData.getEmail() != null) employee.setEmail(updatedData.getEmail());
            registerDetailsRepository.save(employee);
            return "Employee updated successfully.";
        }
        return "Employee not found.";
    }

    public String deleteEmployeeById(int empId) {
        RegisterDetails employee = registerDetailsRepository.findById(empId).orElse(null);
        if (employee != null) {
            registerDetailsRepository.deleteById(empId);
            return "Employee deleted successfully.";
        }
        return "Employee not found.";
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