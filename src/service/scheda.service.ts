import { Injectable } from '@angular/core';
import { AbstractService } from './abstractservice';
import { schedavotazioneDTO } from '../dto/schedavotazionedto';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class SchedaService extends AbstractService<schedavotazioneDTO> {

  constructor(http: HttpClient) {
    super(http);
    this.type = 'schedavotazione';
   }


}
