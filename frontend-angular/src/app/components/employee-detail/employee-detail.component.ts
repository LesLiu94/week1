import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';

@Component({
  selector: 'app-employee-detail',
  templateUrl: './employee-detail.component.html',
  styleUrls: ['./employee-detail.component.scss']
})
export class EmployeeDetailComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<EmployeeDTO>,
    @Inject(MAT_DIALOG_DATA) public data: EmployeeDTO){}
  
  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
