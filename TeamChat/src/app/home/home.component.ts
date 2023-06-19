import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  nav(){
    if (localStorage.hasOwnProperty("id")) {
      this.router.navigate(["/chat"]);
    } else {
      this.router.navigate(["/login"]);
    }
  }
  nav2(){
    this.router.navigate(["/about-us"]);
  }

}
