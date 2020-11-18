import { Component, OnInit } from '@angular/core';
import { schedavotazioneDTO } from '../../../dto/schedavotazionedto';
import { SchedaService } from '../../../service/scheda.service'
import { UtenteVotanteService } from '../../../service/utente-votante.service';
import { UserDTO } from '../../../dto/userdto';

@Component({
  selector: 'app-schede-list',
  templateUrl: './schede-list.component.html',
  styleUrls: ['./schede-list.component.css']
})
export class SchedeListComponent implements OnInit {

  listaSchede: schedavotazioneDTO[];
  checkVoti: Boolean[];
  user: UserDTO;


  constructor(
    private serviceScheda: SchedaService,
    private serviceUtentev: UtenteVotanteService

  ) { }

  ngOnInit() {
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.getLista();
  }

  getLista(){//ci saranno le schede con un check
    this.serviceScheda.getAll().subscribe(listaSchede => this.listaSchede = listaSchede);
    this.listaSchede.forEach(
      scheda => {
        if(this.serviceUtentev.checkUtente(this.user.id, scheda.id) != null)
          this.checkVoti.push(true);
        else
          this.checkVoti.push(false);
      }
    );
  }

  

}
