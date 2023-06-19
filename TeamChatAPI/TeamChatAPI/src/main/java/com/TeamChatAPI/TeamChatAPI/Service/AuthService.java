package com.TeamChatAPI.TeamChatAPI.Service;

import java.util.List;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Repository.EmployeeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService {
    private final EmployeeRepo employeeRepo;
    @Autowired
    public AuthService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    // get all employees
    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }
    public boolean isValid (String username,String lastname,String password){
        boolean valid=false;
        for (Employee e:findAllEmployees()){
            if (username.equals(e.getFirstname()) && lastname.equals(e.getLastname()) && password.equals(e.getPassword())){
                valid=true;
            }
        }
        return valid;
    }

    
}
