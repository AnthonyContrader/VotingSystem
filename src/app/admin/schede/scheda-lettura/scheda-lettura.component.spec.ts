import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedaLetturaComponent } from './scheda-lettura.component';

describe('SchedaLetturaComponent', () => {
  let component: SchedaLetturaComponent;
  let fixture: ComponentFixture<SchedaLetturaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedaLetturaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedaLetturaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
