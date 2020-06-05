import { EmployeeListFilterPipe } from './employee-list-filter.pipe';

describe('EmployeeListFilterPipe', () => {
  it('create an instance', () => {
    const pipe = new EmployeeListFilterPipe();
    expect(pipe).toBeTruthy();
  });
});
