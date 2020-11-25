import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { utentevotanteDTO } from '../dto/utentevotantedto';
import { HttpClient } from '@angular/common/http';
import { schedavotazioneDTO } from '../dto/schedavotazionedto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UtenteVotanteService extends AbstractService<utentevotanteDTO> {

  risultati: number[];
  constructor(
    http: HttpClient
  ) {
    super(http);
    this.type = 'utentevotante';
   }

   checkUtente(
      id_utente: number, 
      id_scheda: number
    ): Observable<schedavotazioneDTO> {
      return this.http.get<schedavotazioneDTO>('http://localhost:' + this.port + '/' + this.type + '/control?id_scheda=' + id_scheda + '&id_utente=' + id_utente);
    } 

    statistica(
      id_scheda: number
    ): Observable<any>{
      return this.http.get<any>('http://localhost:' + this.port + '/' + this.type + '/statistica?id_scheda=' + id_scheda);
    }

    voti(
      id_scheda: number
    ): Observable<any>{
      return this.http.get<any>('http://localhost:' + this.port + '/' + this.type + '/voti?id_scheda=' + id_scheda);
    }
}
