import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeTableService } from 'src/app/services/employee-table/employee-table.service';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { MatDialog} from '@angular/material/dialog';
import { EmployeeDetailComponent } from '../employee-detail/employee-detail.component';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.scss']
})
export class EmployeeTableComponent implements OnInit {

  displayedColumns: string[] = ['index', 'first', 'last', 'title', 'salary'];
  employees: EmployeeDTO[];
  dataSource = new MatTableDataSource<EmployeeDTO>(this.employees);

  constructor(private employeeTableService: EmployeeTableService, public dialog: MatDialog) {}

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
          this.dataSource.filterPredicate = this.filterRowData;
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        });
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  filterRowData(data: EmployeeDTO, filter: string) {
    return data.firstName.toLowerCase().includes(filter) || data.lastName.toLowerCase().includes(filter)
    || data.employeeTitle.toLowerCase().includes(filter) || data.salary.toString().includes(filter);
  }

  displayDetails(employee: EmployeeDTO){
    const dialogRef = this.dialog.open(EmployeeDetailComponent, {
        width: '350px',
        data:employee
    });
  }
}
