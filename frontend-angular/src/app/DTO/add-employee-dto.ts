import { EmployeeTitle } from '../enums/EmployeeTitle';
import { Department } from '../enums/Department';
import { Gender } from '../enums/Gender';

export class AddEmployeeDTO {
    firstName: string;
    lastName: string;
    dateOfBirth: Date;
    employeeTitle: EmployeeTitle;
    department: Department;
    salary: number;
    hireDate: Date;
    gender: Gender;
    fromDate: Date;
    toDate: Date;
    
    constructor() {
        this.firstName = null;
        this.lastName = null;
        this.dateOfBirth = null;
        this.employeeTitle = null;
        this.department = null;
        this.salary = null;
        this.hireDate = null;
        this.gender = null;
        this.fromDate = null;
        this.toDate = null;
    }
}