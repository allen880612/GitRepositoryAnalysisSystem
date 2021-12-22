import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GitanalysisComponent } from './gitanalysis.component';

describe('GitanalysisComponent', () => {
  let component: GitanalysisComponent;
  let fixture: ComponentFixture<GitanalysisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GitanalysisComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GitanalysisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
