package com.TeamChatAPI.TeamChatAPI.Controller;

import com.TeamChatAPI.TeamChatAPI.Model.Employee;
import com.TeamChatAPI.TeamChatAPI.Model.Team;
import com.TeamChatAPI.TeamChatAPI.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees=employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee employee=employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee =employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}") 
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        Employee employee_ =employeeService.updateEmployee(id,employee);
        return new ResponseEntity<>(employee_,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findbyname/{name}")
    public ResponseEntity<Team> getTeamByname(@PathVariable("name") String name){
        Team team=employeeService.findTeam(name);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @GetMapping("/findTeamById/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable("id") Long id){
        Team team=employeeService.findTeamById(id);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @GetMapping("/teammates/{id}")
    public ResponseEntity<List<Employee>> getTeammates(@PathVariable("id") Long id){
        List<Employee> employees=employeeService.findTeammatesByEmployee(id);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/findTeamByEmployee/{employeeid}")
    public ResponseEntity<Team> findTeamByEmployee(@PathVariable("employeeid") Long employeeid){
        Team team=employeeService.findTeamByEmployee(employeeid);
        return new ResponseEntity<>(team, HttpStatus.OK);
    }
    @GetMapping("/findEmployeesBySubstring/{substring}")
    public ResponseEntity<List<Employee>> indEmployeesBySubstring(@PathVariable String substring){
        List<Employee> employees=employeeService.findEmployeesBySubstring(substring);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PutMapping("/setPassword/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,String password){
        Employee employee =employeeService.setPassword(id, password);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

}
