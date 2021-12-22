import { TestBed } from '@angular/core/testing';

import { GitanalysisService } from './gitanalysis.service';

describe('GitanalysisService', () => {
  let service: GitanalysisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GitanalysisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
