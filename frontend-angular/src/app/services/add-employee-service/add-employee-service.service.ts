import { Injectable } from '@angular/core';
import { AddEmployeeDTO } from '../../DTO/add-employee-dto'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { formatDate } from '@angular/common';


const url = 'http://localhost:8080/api/AddEmployee/employee';

@Injectable({
  providedIn: 'root'
})
export class AddEmployeeService {

  constructor(private http: HttpClient) { }


  addEmployee(formData : AddEmployeeDTO): Observable<AddEmployeeDTO>{

    let payload = this.buildPayload(formData);

    return this.http.post<AddEmployeeDTO>(url, payload, {
          headers: new HttpHeaders()
                    .append('Access-Control-Allow-Origin', '*'),
          observe: 'body',
          params: new HttpParams(),
          reportProgress: true,
          responseType: 'json',
          withCredentials: false
        });
  }

  buildPayload(formData : AddEmployeeDTO){
    return {
      firstName: formData.firstName,
      lastName: formData.lastName,
      dob: this.convertDateToString(formData.dateOfBirth),
      employeeTitle: formData.employeeTitle,
      departments: [formData.department],
      salary: formData.salary,
      hireDate: this.convertDateToString(formData.hireDate),
      gender: formData.gender,
      fromDate: this.convertDateToString(formData.fromDate),
      toDate: this.convertDateToString(formData.toDate)
    }
  }

  convertDateToString(date : Date): String{
   return formatDate(date, 'MM/dd/yyyy', 'en', '-0400');
  }
}
