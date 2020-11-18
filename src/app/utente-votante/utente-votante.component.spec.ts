import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UtenteVotanteComponent } from './utente-votante.component';

describe('UtenteVotanteComponent', () => {
  let component: UtenteVotanteComponent;
  let fixture: ComponentFixture<UtenteVotanteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UtenteVotanteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UtenteVotanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
