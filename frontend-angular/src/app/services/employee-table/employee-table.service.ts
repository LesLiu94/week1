import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';

@Injectable({
  providedIn: 'root'
})
export class EmployeeTableService {

  constructor(private http: HttpClient) { }

  private url = "http://localhost:8080/api/EmployeeListLookup/allEmployees";


  getEmployees(): Observable<EmployeeDTO[]>{
    return this.http.get<EmployeeDTO[]>(this.url);
  }

}
