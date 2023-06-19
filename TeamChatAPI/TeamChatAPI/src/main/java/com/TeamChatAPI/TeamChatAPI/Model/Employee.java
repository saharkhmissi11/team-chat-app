package com.TeamChatAPI.TeamChatAPI.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
@Table
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String jobTitle;
    private String phone;
    private String imageUrl;
    private String team;
    @Column(nullable = false,updatable = false)
    private String employeeCode;
    public Employee(){

    }
    public Employee(String firstname,String team,String lastname,String password,String jobTitle,String phone,String imageUrl,String employeeCode){
        this.firstname=firstname;
        this.lastname=lastname;
        this.jobTitle=jobTitle;
        this.imageUrl=imageUrl;
        this.password=password;
        this.team=team;
        this.phone=phone;
        this.employeeCode=employeeCode;
    }

    public Long getId() {
        return id;
    }

    public String getTeam() {
        return team;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
