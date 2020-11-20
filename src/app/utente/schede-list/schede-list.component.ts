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
  checkVoti: boolean[];
  user: UserDTO;
  schedaCheck: schedavotazioneDTO;


  constructor(
    private serviceScheda: SchedaService,
    private serviceUtentev: UtenteVotanteService

  ) { }

  ngOnInit() {
    this.schedaCheck = new schedavotazioneDTO;
    this.checkVoti = [];
    this.listaSchede = [];
    this.user = JSON.parse(localStorage.getItem('currentUser'));
    this.getLista();
  }

  getLista(){//ci saranno le schede con un check
    this.serviceScheda.getAll().subscribe(listaSchede => {this.listaSchede = listaSchede}, undefined, () =>
      this.listaSchede.forEach(
        scheda => {
          //null == ho già votato->false
          //!null == devo ancora votare->true
          this.serviceUtentev.checkUtente(this.user.id, scheda.id).subscribe(
            schedaCheck => {this.schedaCheck = schedaCheck},
            undefined,
            () => {
              if(this.schedaCheck != null)
                this.checkVoti.push(true);
              else
                this.checkVoti.push(false);  
            });    
        }    
    )); 
      
  } 
}
