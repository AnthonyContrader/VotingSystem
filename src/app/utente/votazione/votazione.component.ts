import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { utentevotanteDTO } from 'src/dto/utentevotantedto';
import { schedavotazioneDTO } from '../../../dto/schedavotazionedto';
import { SchedaService } from '../../../service/scheda.service';
import { UtenteVotanteService } from '../../../service/utente-votante.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';



@Component({
  selector: 'app-votazione',
  templateUrl: './votazione.component.html',
  styleUrls: ['./votazione.component.css']
})
export class VotazioneComponent implements OnInit {


  schedaDTO: schedavotazioneDTO;
  voto: number;
  id_scheda: number;
  utenteVtoinsert: utentevotanteDTO;
  utenteV: UserDTO;
  constructor(
    private schedaService: SchedaService,
    private utenteService: UtenteVotanteService,
    private activatedRoute: ActivatedRoute,
    private router: Router

  ) { }

  ngOnInit() {
    this.schedaDTO = new schedavotazioneDTO;
    this.utenteVtoinsert = new utentevotanteDTO;
    this.utenteV = JSON.parse(localStorage.getItem('currentUser'));
    this.utenteVtoinsert.id_utente = this.utenteV.id;
    
    this.activatedRoute.params.subscribe(
      scheda => {
        this.id_scheda = scheda['id_scheda'];
        this.utenteVtoinsert.id_scheda = scheda['id_scheda'];
      },
      undefined,
      null
    );

    this.getScheda();
  }

  getScheda(){
    this.schedaService.read(this.id_scheda).subscribe(schedaDTO => {this.schedaDTO = schedaDTO}, undefined, null);
  }

  submit(utenteVot: utentevotanteDTO){
    this.utenteService.insert(utenteVot).subscribe(null,undefined,null);
    this.router.navigate(['/user-dashboard']);
  }

}
