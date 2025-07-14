//package com.example.springbootfirst.services;
//
//import com.example.springbootfirst.models.RegisterDetails;
//import com.example.springbootfirst.repository.RegisterDetailsRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private RegisterDetailsRepository registerDetailsRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        RegisterDetails user = registerDetailsRepository.findByUserName(userName)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userName));
//
//        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
//
//        System.out.println("✅ Login attempt for: " + user.getUserName());
//        System.out.println("🔐 Password (hashed): " + user.getPassword());
//        System.out.println("🧑 Roles: " + authorities);
//
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserName(),
//                user.getPassword(),
//                authorities
//        );
//    }
//
//}


package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        3 things
        1. Loading data from your database
        2. setting up the authorities
        3. returning up proper UserDetails
         */

        //Step 1
        RegisterDetails user = registerDetailsRepository.findByUserName(username)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        //Step 2
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName()))
                .collect(Collectors.toSet());
        System.out.println("Username is " + user.getUserName() + "\nPassword is " + user.getPassword() + "\nAuthority is " + authorities);
        return new User(user.getUserName(), user.getPassword() ,authorities);
    }
}