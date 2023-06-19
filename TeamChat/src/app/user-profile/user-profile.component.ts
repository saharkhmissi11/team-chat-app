import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { EmployeeService } from '../services/employee.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  id:number
  employee:any={}
  constructor(private employeeService:EmployeeService,private authService:AuthService) { }

  ngOnInit(): void {
    
    this.employee=this.authService.loggedEmployee
  }

}
