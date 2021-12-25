import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SonarqubeComponent } from './sonarqube.component';

describe('SonarqubeComponent', () => {
  let component: SonarqubeComponent;
  let fixture: ComponentFixture<SonarqubeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SonarqubeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SonarqubeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
