import { TestBed } from '@angular/core/testing';

import { LookupEmployeeService } from '../lookup-employee.service';

describe('LookupEmployeeService', () => {
  let service: LookupEmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LookupEmployeeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
