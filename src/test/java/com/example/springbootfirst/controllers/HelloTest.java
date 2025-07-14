package com.example.springbootfirst.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HelloTest {
    @Test
    public void testHelloTest(){
        Hello h1 = new Hello();
        String str = h1.helloTest();
        System.out.println(str);
        assertEquals("Hello Test",str);
    }
}
