import { TestBed } from '@angular/core/testing';

import { SonarqubeService } from './sonarqube.service';

describe('SonarqubeService', () => {
  let service: SonarqubeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SonarqubeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
