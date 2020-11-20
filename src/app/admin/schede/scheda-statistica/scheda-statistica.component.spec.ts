import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedaStatisticaComponent } from './scheda-statistica.component';

describe('SchedaStatisticaComponent', () => {
  let component: SchedaStatisticaComponent;
  let fixture: ComponentFixture<SchedaStatisticaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedaStatisticaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedaStatisticaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
