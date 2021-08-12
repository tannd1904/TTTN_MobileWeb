import { TestBed } from '@angular/core/testing';

import { AbstracService } from './abstrac.service';

describe('AbstracService', () => {
  let service: AbstracService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbstracService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
