import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HotelService } from '../hotel.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  message: string;
  error: string;
  constructor(private router: Router, private hotel: HotelService) { }

  printForm(registerForm: NgForm) {
    console.log(registerForm.value);
    this.hotel.userRegister(registerForm.value).subscribe(data => {
      console.log(data);
      if (data && data.statusCode === 201) {
        this.message = data.description;
        registerForm.reset();
        this.router.navigateByUrl('/login');
      } else if (data && data.statusCode === 401) {
        this.error = data.description;
      }
    }, err => {
      console.log(err);
    });
  }
  ngOnInit() {
  }

}
