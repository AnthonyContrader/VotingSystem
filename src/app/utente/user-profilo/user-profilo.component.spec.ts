import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfiloComponent } from './user-profilo.component';

describe('UserProfiloComponent', () => {
  let component: UserProfiloComponent;
  let fixture: ComponentFixture<UserProfiloComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserProfiloComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfiloComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
