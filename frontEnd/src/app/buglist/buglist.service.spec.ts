import { TestBed } from '@angular/core/testing';

import { BuglistService } from './buglist.service';

describe('BuglistService', () => {
  let service: BuglistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuglistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
