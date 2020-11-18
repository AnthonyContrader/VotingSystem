import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { schedavotazioneDTO } from '../../../dto/schedavotazionedto';
import { SchedaService } from '../../../service/scheda.service';

@Component({
  selector: 'app-votazione',
  templateUrl: './votazione.component.html',
  styleUrls: ['./votazione.component.css']
})
export class VotazioneComponent implements OnInit {

  schedaDTO: schedavotazioneDTO;
  id_scheda: number;
  constructor(
    private activatedRoute: ActivatedRoute,
    private schedaService: SchedaService
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      scheda => {
        this.id_scheda = scheda['id_scheda'];
      }
    );

    this.getScheda();
  }

  getScheda(){
    this.schedaService.read(this.id_scheda).subscribe(schedaDTO => this.schedaDTO = schedaDTO);
  }



}
