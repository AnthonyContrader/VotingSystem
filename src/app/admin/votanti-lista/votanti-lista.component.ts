import { Component, OnInit } from '@angular/core';
import { utentevotanteDTO } from '../../../dto/utentevotantedto';
import { UtenteVotanteService } from '../../../service/utente-votante.service';

 

@Component({
  selector: 'app-votanti-lista',
  templateUrl: './votanti-lista.component.html',
  styleUrls: ['./votanti-lista.component.css']
})
export class VotantiListaComponent implements OnInit {

  searchId: string;
  listaUserV: utentevotanteDTO[];
  constructor(
    private service: UtenteVotanteService,
    
  ) { }

  ngOnInit() {
    this.getList();
  }

  getList(){
    this.service.getAll().subscribe(listaUserV => {this.listaUserV = listaUserV},undefined,null);
  }

}
