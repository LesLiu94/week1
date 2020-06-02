import { Component, OnInit} from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { EmployeeTitle } from '../../enums/EmployeeTitle';
import { Department } from '../../enums/Department';
import { Gender } from '../../enums/Gender';
import { AddEmployeeDTO } from '../../DTO/add-employee-dto';
import { AddEmployeeService } from '../../services/add-employee-service/add-employee-service.service';

@Component({
  selector: 'add-employee-modal-button',
  templateUrl: './add-employee-modal-button.component.html',
  styleUrls: ['./add-employee-modal.component.scss'],
})
export class AddEmployeeModalButton {

  constructor(public dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(AddEmployeeModalComponent, {
      width: '500px',
      height: '750px'
    });
  }

}


@Component({
  selector: 'app-add-employee-modal',
  templateUrl: './add-employee-modal.component.html',
  styleUrls: ['./add-employee-modal.component.scss']
})
export class AddEmployeeModalComponent implements OnInit {

  EmployeeTitles: Object;
  Departments: Object;
  Genders: Object;

  form: AddEmployeeDTO;

  employees: AddEmployeeDTO[];

  constructor(
    public dialogRef: MatDialogRef<AddEmployeeModalComponent>,
    private addEmployeeService: AddEmployeeService) {}
		
	ngOnInit(): void {
    this.EmployeeTitles = Object.keys(EmployeeTitle);
    this.Departments = Object.keys(Department);
    this.Genders = Object.values(Gender);
    this.form = new AddEmployeeDTO();
    this.employees = [];
	}

	save(): void {

    this.addEmployeeService.addEmployee(this.form)
      .subscribe(employee=>this.employees.push(employee));

		// this.close();
	}
  
  close(): void {
    this.dialogRef.close();
  }

  hasMissingFields(): boolean {
    let emptyFields = Object.getOwnPropertyNames(this.form)
      .filter(fieldName=>!this.form[fieldName]);

    return emptyFields.length>0
  }

}
