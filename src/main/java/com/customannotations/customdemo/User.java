package com.customannotations.customdemo;

import org.springframework.stereotype.Component;

// custom exception
class MyCustomException extends Exception {
    MyCustomException(String message) {
        super(message);
    }
}

@Component
public class User {
    @DBField(name = "userid", type = Integer.class, isPrimary = true)
    private int userid;

    @DBField(name = "username", type = String.class)
    private String username;

    @DBField(name = "email", type = String.class)
    private String email;

    private int a; // not annotated, to demonstrate field.getAnnotation returns null pointer

    public void setA(int a){
        this.a = a;
    }

    public int getA(){
        return a;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @TrackExecutionTime
    public void getAllUsers(){ // throws Exception{
        try {
            Thread.sleep(3000); // inducing lag to calculate the execution time of this method
            throw new MyCustomException("Thread Interrupted"); // to see how @AfterThrowing handles the error thrown
        } catch(MyCustomException e){
            System.out.println("The exception: " + e);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fetched all users");
    }

    public void deleteUser() {
        System.out.println("\nUser deleted\n");
    }

    @TrackExecutionTime
    public String printUser(){
        try{
            Thread.sleep(1000); // inducing sleep
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Printing a user");
        return "Anonymous"; // to see how @AfterReturning gets the return value
    }
}
