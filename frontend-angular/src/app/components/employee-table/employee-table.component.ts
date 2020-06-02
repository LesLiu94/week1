import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeTableService } from 'src/app/services/employee-table/employee-table.service';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource, MatTable } from '@angular/material/table'; 

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.sass']
})
export class EmployeeTableComponent implements OnInit {

  displayedColumns: string[] = ['index', 'first', 'last', 'title', 'salary'];
  employees: EmployeeDTO[];
  dataSource = new MatTableDataSource<EmployeeDTO>(this.employees);

  constructor(private employeeTableService: EmployeeTableService) {}

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  ngOnInit(): void {
    this.getEmployees();
    this.dataSource.paginator = this.paginator;
  }

  getEmployees(){
    this.employeeTableService.getEmployees()
      .subscribe(employees => this.employees = employees);
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  //added tracking for the table for performance optimization.
  //for example:
  //when a new employee is added, only a new row will be created instead of destroying the whole table and recreating every entry
  trackByEmpNo(index: number, employee:any): string {
    return employee.empNo;
  }

}
