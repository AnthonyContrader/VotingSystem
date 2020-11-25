import { Component, OnInit } from '@angular/core';
import { UtenteVotanteService } from '../../../../service/utente-votante.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../../../service/user.service';
import { UserDTO } from '../../../../dto/userdto';

@Component({
  selector: 'app-scheda-statistica',
  templateUrl: './scheda-statistica.component.html',
  styleUrls: ['./scheda-statistica.component.css']
})
export class SchedaStatisticaComponent implements OnInit {


  ris: number[];
  voti: number[];
  users: UserDTO[];
  allUsers: number;
  predictVoto: number;
  id_scheda: number;
  constructor(
    private service: UtenteVotanteService,
    private activatedroute: ActivatedRoute,
    private userSevice: UserService
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
  
  fnPredict(){
    //N utenti si/(N utenti tot - N utenti invalido) 
    //N utenti no/(N utenti tot - N utenti invalido) 
    this.service.voti(this.id_scheda).subscribe(voti => {this.voti = voti}, undefined, () =>{
      this.userSevice.getAll().subscribe(users => {this.users = users}, undefined, () =>{
        this.allUsers = this.users.length;         
        if ((this.voti[0]/(this.allUsers - this.voti[2])) > (this.voti[1]/(this.allUsers - this.voti[2]))){
          this.predictVoto = 0;
        } else if((this.voti[0]/(this.allUsers - this.voti[2])) < (this.voti[1]/(this.allUsers - this.voti[2]))){
          this.predictVoto = 1;
        } else {
          this.predictVoto = 2;
        }   
      });  
    });
  }

}
