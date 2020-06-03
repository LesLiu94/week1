import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeTableService } from 'src/app/services/employee-table/employee-table.service';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table'; 

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.scss']
})
export class EmployeeTableComponent implements OnInit {

  displayedColumns: string[] = ['index', 'first', 'last', 'title', 'salary'];
  employees: EmployeeDTO[];
  dataSource = new MatTableDataSource<EmployeeDTO>(this.employees);

  constructor(private employeeTableService: EmployeeTableService) {}

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(){
    this.employeeTableService.getEmployees()
      .subscribe(
        employees => {
          this.employees = employees;
          this.dataSource = new MatTableDataSource<EmployeeDTO>(Object.values(employees));
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
