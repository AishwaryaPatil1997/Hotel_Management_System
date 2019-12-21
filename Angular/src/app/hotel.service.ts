import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  userLogin(data: any) {
    return this.http.put<any>(`http://localhost:8080/userLogin?email=${data.email}&password=${data.password}`, data);
  }


  userRegister(data: any) {
    return this.http.post<any>(`http://localhost:8080/registerUser`, data);
  }
}
