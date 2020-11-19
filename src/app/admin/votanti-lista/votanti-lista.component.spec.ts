import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VotantiListaComponent } from './votanti-lista.component';

describe('VotantiListaComponent', () => {
  let component: VotantiListaComponent;
  let fixture: ComponentFixture<VotantiListaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VotantiListaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VotantiListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
