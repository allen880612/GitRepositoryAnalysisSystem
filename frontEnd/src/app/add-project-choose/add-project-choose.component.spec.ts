import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddProjectChooseComponent } from './add-project-choose.component';

describe('AddProjectChooseComponent', () => {
  let component: AddProjectChooseComponent;
  let fixture: ComponentFixture<AddProjectChooseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddProjectChooseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProjectChooseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
