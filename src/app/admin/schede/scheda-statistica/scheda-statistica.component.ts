import { Component, OnInit } from '@angular/core';
import { UtenteVotanteService } from '../../../../service/utente-votante.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-scheda-statistica',
  templateUrl: './scheda-statistica.component.html',
  styleUrls: ['./scheda-statistica.component.css']
})
export class SchedaStatisticaComponent implements OnInit {


  ris: number[];
  id_scheda: number;
  constructor(
    private service: UtenteVotanteService,
    private activatedroute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.ris = [];
    this.activatedroute.params.subscribe(
      sch => {
        this.id_scheda = sch['id'];        
      },
      undefined,
      null
    );
    this.fnStat();
  }

  fnStat(){
    this.service.statistica(this.id_scheda).subscribe(ris => {this.ris = ris},undefined,null);
  }

}
