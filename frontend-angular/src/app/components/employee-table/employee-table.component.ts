import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeTableService } from 'src/app/services/employee-table/employee-table.service';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { EmployeeListFilterPipe } from '../../pipes/employee-list-filter.pipe';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.scss'],
  providers: [EmployeeListFilterPipe]
})
export class EmployeeTableComponent implements OnInit {

  displayedColumns: string[] = ['index', 'first', 'last', 'title', 'salary'];
  employees: EmployeeDTO[];
  dataSource = new MatTableDataSource<EmployeeDTO>(this.employees);

  constructor(private employeeTableService: EmployeeTableService, private pipe: EmployeeListFilterPipe) {}

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  ngOnInit(): void {
    this.dataSource.filterPredicate = function(data: EmployeeDTO, filter: string): boolean {
      return data.firstName.toLowerCase().includes(filter) || data.lastName.toLowerCase().includes(filter)
      || data.employeeTitle.toLowerCase().includes(filter) || data.salary.toString().includes(filter);
    }
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

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
