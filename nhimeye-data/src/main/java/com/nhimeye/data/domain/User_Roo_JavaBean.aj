// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nhimeye.data.domain;

import com.nhimeye.data.domain.User;

privileged aspect User_Roo_JavaBean {
    
    public String User.getUserName() {
        return this.userName;
    }
    
    public void User.setUserName(String userName) {
        this.userName = userName;
    }
    
    public String User.getPassword() {
        return this.password;
    }
    
    public void User.setPassword(String password) {
        this.password = password;
    }
    
    public String User.getFirstName() {
        return this.firstName;
    }
    
    public void User.setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String User.getLastName() {
        return this.lastName;
    }
    
    public void User.setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String User.getEmail() {
        return this.email;
    }
    
    public void User.setEmail(String email) {
        this.email = email;
    }
    
    public Boolean User.getActive() {
        return this.active;
    }
    
    public void User.setActive(Boolean active) {
        this.active = active;
    }
    
}
