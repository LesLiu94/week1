import { Injectable } from '@angular/core';
import { AddEmployeeDTO } from '../../DTO/add-employee-dto'
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators';


const url = 'http://localhost:8080/api/AddEmployee/employee';
const options = {
  headers: new HttpHeaders(),
  observe: 'body',
  params: new HttpParams(),
  reportProgress: true,
  responseType: 'json',
  withCredentials: false
}

@Injectable({
  providedIn: 'root'
})
export class AddEmployeeService {

  constructor(private http: HttpClient) { }


  addEmployee(formData : AddEmployeeDTO): Observable<any>{
    //send form data to backend
    return this.http.post(url, options);
  }
}
