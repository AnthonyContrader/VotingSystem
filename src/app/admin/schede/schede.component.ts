import { Component, OnInit } from '@angular/core';
import { SchedaService } from '../../../service/scheda.service';
import { UtenteVotanteService } from '../../../service/utente-votante.service';
import { schedavotazioneDTO } from '../../../dto/schedavotazionedto';

@Component({
  selector: 'app-schede',
  templateUrl: './schede.component.html',
  styleUrls: ['./schede.component.css']
})
export class SchedeComponent implements OnInit {

  schedeList: schedavotazioneDTO[];
  schedatoinsert: schedavotazioneDTO = new schedavotazioneDTO();
  constructor(
    private serviceScheda: SchedaService,
    private userVservice: UtenteVotanteService
  ) { }

  ngOnInit() {
    this.getSchede();
  }

  getSchede() {
    this.serviceScheda.getAll().subscribe(schedeList => {this.schedeList = schedeList}, undefined, null);
  }

  delete(sch: schedavotazioneDTO) {
    this.serviceScheda.delete(sch.id).subscribe(() => {this.getSchede()}, undefined, null);
  }

  

  insert(sch: schedavotazioneDTO) {
    this.serviceScheda.insert(sch).subscribe(() => {this.getSchede()}, undefined, null);
  }

  clear(){
    this.schedatoinsert = new schedavotazioneDTO();
  }

}
