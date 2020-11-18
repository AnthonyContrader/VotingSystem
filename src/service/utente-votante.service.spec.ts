import { TestBed } from '@angular/core/testing';

import { UtenteVotanteService } from './utente-votante.service';

describe('UtenteVotanteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UtenteVotanteService = TestBed.get(UtenteVotanteService);
    expect(service).toBeTruthy();
  });
});
