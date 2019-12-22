import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  isUserLogin() {
    const user = JSON.parse(localStorage.getItem('key'));
    if (user && user.userType === 'user') {
      return true;
    } else {
      return false;
    }
  }
  ngOnInit() {
  }

}
