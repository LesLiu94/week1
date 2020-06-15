import { EmployeeTitle } from '../enums/EmployeeTitle';
import { Department } from '../enums/Department';
import { Gender } from '../enums/Gender';

export interface EmployeeDTO {
    firstName: String;
    lastName: String;
    dob: Date;
    employeeTitle: EmployeeTitle;
    departments: Department[];
    salary: number;
    empNo: number;
    isActive: boolean;
    hireDate: Date;
    gender: Gender;
    fromDate: Date;
    toDate: Date;
    unequallyPaid: boolean;
}