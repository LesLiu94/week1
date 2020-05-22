import { Component, OnInit} from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { EmployeeTitle } from '../../enums/EmployeeTitle';
import { Department } from '../../enums/Department';
import { Gender } from '../../enums/Gender';



@Component({
  selector: 'add-employee-modal-button',
  templateUrl: './add-employee-modal-button.component.html',
  styleUrls: ['./add-employee-modal.component.sass'],
})
export class AddEmployeeModalButton {

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(AddEmployeeModalComponent, {
      width: '250px',
      height: '500px'
    });
  }

}


@Component({
  selector: 'app-add-employee-modal',
  templateUrl: './add-employee-modal.component.html',
  styleUrls: ['./add-employee-modal.component.sass']
})
export class AddEmployeeModalComponent implements OnInit {

  EmployeeTitles: Object;
  Departments: Object;
  Genders: Object;

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

  constructor(
		public dialogRef: MatDialogRef<AddEmployeeModalComponent>) {}
		
	ngOnInit(): void {
    this.EmployeeTitles = Object.keys(EmployeeTitle);
    this.Departments = Object.keys(Department);
    this.Genders = Object.values(Gender);
	}

	save(): void {
    //save input
		this.close();
	}
  
  close(): void {
    this.dialogRef.close();
  }

}
