import { Pipe, PipeTransform } from '@angular/core';
import { EmployeeDTO } from '../DTO/employee-dto';

@Pipe({
  name: 'employeeListFilter'
})
export class EmployeeListFilterPipe implements PipeTransform {

  transform(items: EmployeeDTO[], value: string): EmployeeDTO[] {
    if(!items) return [];
    if(!value) return items;

    items.filter(item =>
      item.firstName.toLowerCase().includes(value.toLowerCase()) || 
      item.lastName.toLowerCase().includes(value.toLowerCase()) ||
      item.employeeTitle.toLowerCase().includes(value.toLowerCase()) ||
      item.salary.toString().includes(value));

    return items;
  }

}
