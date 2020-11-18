import { Component, OnInit } from '@angular/core';
import { utentevotanteDTO } from '../../dto/utentevotantedto';
import { UtenteVotanteService } from '../../service/utente-votante.service';

@Component({
  selector: 'app-utente-votante',
  templateUrl: './utente-votante.component.html',
  styleUrls: ['./utente-votante.component.css']
})
export class UtenteVotanteComponent implements OnInit {
  usersv: utentevotanteDTO[];
  uservtoinsert: utentevotanteDTO = new utentevotanteDTO();

  constructor(
    private service: UtenteVotanteService
    ) { }

  ngOnInit() {
    this.getUsersv();
  }

  getUsersv() {
    this.service.getAll().subscribe(usersv => this.usersv = usersv);
  }

  insert(userv: utentevotanteDTO) {
    this.service.insert(userv).subscribe(() => this.getUsersv());
  }


}
