import { TestBed } from '@angular/core/testing';

import { ProjectAnalysisService } from './project-analysis.service';

describe('ProjectAnalysisService', () => {
  let service: ProjectAnalysisService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProjectAnalysisService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
