package com.customannotations.customdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// configuration file for AOP advices through Java
// generating beans automatically through @ComponentScan
@Configuration
@ComponentScan("com.customannotations.customdemo")
@EnableAspectJAutoProxy
public class CustomConfig {
    
}