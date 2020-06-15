import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeTableService } from 'src/app/services/employee-table/employee-table.service';
import { EmployeeDTO } from 'src/app/DTO/employee-dto';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource, MatTable } from '@angular/material/table';
import { MatDialog} from '@angular/material/dialog';
import { EmployeeDetailComponent } from '../employee-detail/employee-detail.component';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-employee-table',
  templateUrl: './employee-table.component.html',
  styleUrls: ['./employee-table.component.scss']
})
export class EmployeeTableComponent implements OnInit {

  displayedColumns: string[] = ['index', 'first', 'last', 'title', 'salary'];
  employees: EmployeeDTO[];
  dataSource = new MatTableDataSource<EmployeeDTO>(this.employees);
  specialFilterCharacter: string = ' ';

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

  applyFilter(filterValue: string, useAlternateDefaultValue: boolean) {
    let defaultFilterValue = useAlternateDefaultValue ? this.specialFilterCharacter : '';
    this.dataSource.filter = filterValue ? filterValue.trim().toLowerCase() : defaultFilterValue;
  }
  
  toggleUnequallyPaidFilter(changeEvent: MatSlideToggleChange){
    if (changeEvent.checked) {
      this.dataSource.filterPredicate = this.filterRowDataWithUnequallyPaid;
      this.applyFilter(this.dataSource.filter, true);
    }else{
      this.dataSource.filterPredicate = this.filterRowData;
      this.applyFilter(this.dataSource.filter, false);
    }
  }
  
  filterRowData(data: EmployeeDTO, filter: string) {
    return data.firstName.toLowerCase().includes(filter) 
    || data.lastName.toLowerCase().includes(filter)
    || data.employeeTitle.toLowerCase().includes(filter) 
    || data.salary.toString().includes(filter)
  }

  filterRowDataWithUnequallyPaid(data: EmployeeDTO, filter: string) {
    var trimmedFilterValue = filter.trim().toLowerCase();
    return (data.firstName.toLowerCase().includes(trimmedFilterValue) 
    || data.lastName.toLowerCase().includes(trimmedFilterValue)
    || data.employeeTitle.toLowerCase().includes(trimmedFilterValue) 
    || data.salary.toString().includes(trimmedFilterValue)) && data.unequallyPaid;
  }

  displayDetails(employee: EmployeeDTO){
    const dialogRef = this.dialog.open(EmployeeDetailComponent, {
        width: '350px',
        data:employee
    });
  }
}
