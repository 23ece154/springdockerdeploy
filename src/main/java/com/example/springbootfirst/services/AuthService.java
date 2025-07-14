//package com.example.springbootfirst.services;
//
//import com.example.springbootfirst.jwt.JwtTokenProvider;
//import com.example.springbootfirst.models.Roles;
//import com.example.springbootfirst.models.RegisterDetails;
//import com.example.springbootfirst.models.UserDetailsDto;
//import com.example.springbootfirst.repository.RolesRepository;
//import com.example.springbootfirst.repository.RegisterDetailsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class AuthService {
//
////    @Autowired
////    RegisterDetailsRepository registerDetailsRepository;
////
////    @Autowired
////    RolesRepository rolesRepository;
////
////    @Autowired
////    PasswordEncoder passwordEncoder;
////
////    public String registerNewUser(UserDetailsDto request){
////        RegisterDetails user = new RegisterDetails();
////        user.setName(request.getName());
////        user.setUserName(request.getUserName());
////        user.setEmail(request.getEmail());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        Set<Roles> roles = new HashSet<>();
////        for (String roleName : request.getRoleNames()) {
////            Roles role = rolesRepository.findByName(roleName)
////                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
////            roles.add(role);
////        }
////        user.setRoles(roles);
////        registerDetailsRepository.save(user);
////        return "Employee Registered Successfully";
////    }
////
////    public boolean authenticate(String userName, String rawPassword) {
////        Optional<RegisterDetails> userOptional = registerDetailsRepository.findByUserName(userName);
////        if (userOptional.isEmpty()) {
////            return false;
////        }
////
////        RegisterDetails user = userOptional.get();
////        return passwordEncoder.matches(rawPassword, user.getPassword());
////    }
//
//    @Autowired
//    RegisterDetailsRepository registerRepository;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    RegisterDetailsRepository registerDetailsRepository;
//
//    @Autowired
//    RolesRepository rolesRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public String addNewEmployee(UserDetailsDto register) {
//        RegisterDetails registerDetails = new RegisterDetails();
//        registerDetails.setEmpId(register.getEmpId());
//        registerDetails.setName(register.getName());
//        registerDetails.setEmail(register.getEmail());
//        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
//        registerDetails.setUserName(register.getUserName());
//        Set<Roles> roles = new HashSet<>();
//        for(String roleName: register.getRoleNames()){
//            Roles role = (Roles) rolesRepository.findByRoleName(roleName)
//                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
//            roles.add(role);
//        }
//        registerDetails.setRoles(roles);
//        System.out.println("Registration"+ registerDetails);
//        registerDetailsRepository.save(registerDetails);
//        return "Employee Added Successfully";
//    }
//
//    public String authenticate(RegisterDetails login){
//        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserName(),login.getPassword()))
//                ;
//        return jwtTokenProvider.generateToken(authentication);
//    }
//    public Optional<RegisterDetails>  getUserByName(String username){
//        return  registerRepository.findByUserName(username);
//    }
//
//}


package com.example.springbootfirst.services;

import com.example.springbootfirst.jwt.JwtTokenProvider;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RegisterRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

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

    public String authenticate(RegisterDetails login) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                login.getUserName(),login.getPassword()));
        return jwtTokenProvider.generateToken(authentication);
    }

    public Optional<RegisterDetails> getUserByUsername(String username){
        return registerRepository.findByUserName(username);
    }
}