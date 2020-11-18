import { Component, OnInit } from '@angular/core';
import { SchedaService } from '../../service/scheda.service';
import { schedavotazioneDTO } from '../../dto/schedavotazionedto';

@Component({
  selector: 'app-scheda',
  templateUrl: './scheda.component.html',
  styleUrls: ['./scheda.component.css']
})
export class SchedaComponent implements OnInit {
  schede: schedavotazioneDTO[];
  schedatoinsert: schedavotazioneDTO = new schedavotazioneDTO();

  constructor(private service: SchedaService) { }

  ngOnInit() {
    this.getSchede();
  }

  getSchede() {
    this.service.getAll().subscribe(schede => this.schede = schede);
  }

  delete(scheda: schedavotazioneDTO) {
    this.service.delete(scheda.id).subscribe(() => this.getSchede());
  }

  update(scheda: schedavotazioneDTO) {
    this.service.update(scheda).subscribe(() => this.getSchede());
  }

  insert(scheda: schedavotazioneDTO) {
    this.service.insert(scheda).subscribe(() => this.getSchede());
  }

  clear(){
    this.schedatoinsert = new schedavotazioneDTO();
  }

}
