package com.TeamChatAPI.TeamChatAPI.Service;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Message;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import com.TeamChatAPI.TeamChatAPI.Repository.EmployeeRepo;
import com.TeamChatAPI.TeamChatAPI.Repository.TeamRepo;
import com.TeamChatAPI.TeamChatAPI.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final TeamRepo teamRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,TeamRepo teamRepo) {
        this.employeeRepo = employeeRepo;
        this.teamRepo=teamRepo;
    }
    // Add employee
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeRepo.save(employee);
    }
    // get all employees
    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }
    //update employee
    public Employee updateEmployee(Long id,Employee e){
        Employee employee=employeeRepo.findEmployeeById(id).orElseThrow(()->new UserNotFoundException("employee by id"+id+"was not found"));
        employee.setLastname(e.getLastname());
        employee.setFirstname(e.getFirstname());
        employee.setImageUrl(e.getImageUrl());
        employee.setJobTitle(e.getJobTitle());
        employee.setPhone(e.getPhone());
        return employeeRepo.save(employee);
    }
    // get employee by id
    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).orElseThrow(()->new UserNotFoundException("employee by id"+id+"was not found"));
    }
    //get employees by susbstring
    public List<Employee> findEmployeesBySubstring(String substring) {
        List<Employee> matchingEmployees =new ArrayList<>();
        List<Employee> employees=this.findAllEmployees();
        for (Employee employee : employees) {
            if ((employee.getFirstname()+employee.getLastname()).replace(" ", "").toLowerCase().contains(substring.replace(" ", "").toLowerCase())) {
                matchingEmployees.add(employee);
            }
        }
        return matchingEmployees;
    }
    // delete employee by id
    public void deleteEmployeeById(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
    // find team
    public Team findTeam(String name){
        return teamRepo.findTeamByName(name).orElseThrow(()->new UserNotFoundException("team by name"+name+"was not found"));
    }
    public Team findTeamById(Long id){
        return teamRepo.findTeamById(id).orElseThrow(()->new UserNotFoundException("team by name"+id+"was not found"));
    }
    public Team findTeamByEmployee(Long employeeid){
        Employee employee=this.findEmployeeById(employeeid);
        String teamName=employee.getTeam();
        return this.findTeam(teamName);
    }
    // find teammates
    public List<Employee> findTeammatesByEmployee(Long id){
       Employee employee=findEmployeeById(id);
       String name=employee.getTeam();
        Team team=this.findTeam(name);
        return team.getEmployees();
    }
    //Get Messages
    public List<Message> getMessages(Long id){
        Employee employee=findEmployeeById(id);
        String name=employee.getTeam();
         Team team=this.findTeam(name);
         return team.getMessages();
    }
    //set password 
    public Employee setPassword(Long id,String password){
        Employee employee=employeeRepo.findEmployeeById(id).orElseThrow(()->new UserNotFoundException("employee by id"+id+"was not found"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        employee.setPassword(hashedPassword);
        employee.setPassword(password);
        return employeeRepo.save(employee);

    }
    
}
