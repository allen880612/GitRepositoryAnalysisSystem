import { TestBed } from '@angular/core/testing';

import { VerifySonarProjectService } from './verify-sonar-project.service';

describe('VerifySonarProjectService', () => {
  let service: VerifySonarProjectService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerifySonarProjectService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
