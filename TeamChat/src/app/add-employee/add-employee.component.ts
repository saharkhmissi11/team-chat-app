import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Employee } from 'class/Employee';
import { EmployeeService } from '../services/employee.service';
import { EventEmitter, Input, Output } from '@angular/core';
import * as bcrypt from 'bcryptjs';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  employee:any={}
  options = [
    { name: 'DevOps' },
    {  name: 'Security' },
    {  name: 'Support' }
  ];


 
  constructor(private employeeService:EmployeeService) { 
  
  }
  
  
  ngOnInit(): void {
    const plaintext = '123';
const hash = '$2a$10$.QsF/pioO3m6lR8DJD2/0em.FDraT4lU4WrHHfLeapo023/3Q20S2';
console.log(bcrypt.compareSync(plaintext, hash))

  }
  // Add Employee
  addEmployee(addForm:NgForm){
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response:Employee)=>{console.log(response)},
      (error:HttpErrorResponse)=>{alert(error.message)}
    )
    console.log(this.employee)
  } 
 
}
