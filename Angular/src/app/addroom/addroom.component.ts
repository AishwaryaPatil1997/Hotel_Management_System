import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-addroom',
  templateUrl: './addroom.component.html',
  styleUrls: ['./addroom.component.css']
})
export class AddroomComponent implements OnInit {

  constructor(private router: Router) { }

  addRoom() {
    this.router.navigateByUrl('/');
  }

  ngOnInit() {
  }

}
