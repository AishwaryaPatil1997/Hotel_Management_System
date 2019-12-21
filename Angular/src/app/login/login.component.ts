import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from '../hotel.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  error: string;


  constructor(private router: Router, private hotel: HotelService) { }

  printForm(loginForm: NgForm) {
    console.log(loginForm.value);
    this.hotel.userLogin(loginForm.value).subscribe(data => {
      console.log(data);
      if (data && data.statusCode === 201) {
        localStorage.setItem('key', JSON.stringify(data.userBean));
        this.router.navigateByUrl('/');
      } else if (data && data.statusCode === 401) {
        this.error = data.description;
      }
    }, err => {
      console.log(err);
      this.error = err.error.error.message;
    });
  }

  ngOnInit() {
  }

}
