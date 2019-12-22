import { Component, OnInit } from '@angular/core';
import { HotelService } from '../hotel.service';

@Component({
  selector: 'app-addhotel',
  templateUrl: './addhotel.component.html',
  styleUrls: ['./addhotel.component.css']
})
export class AddhotelComponent implements OnInit {

  hotels: Hotel[];
  
  constructor(private router: Router,private hotel:HotelService) { }




  ngOnInit() {
  }

}
