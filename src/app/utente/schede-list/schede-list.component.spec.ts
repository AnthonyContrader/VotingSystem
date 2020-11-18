import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SchedeListComponent } from './schede-list.component';

describe('SchedeListComponent', () => {
  let component: SchedeListComponent;
  let fixture: ComponentFixture<SchedeListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SchedeListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SchedeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
