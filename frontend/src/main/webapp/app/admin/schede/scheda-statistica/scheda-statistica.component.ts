import { Component, OnInit } from '@angular/core';
import { User } from '../../../core/user/user.model';
import { UserService } from '../../../core/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { VotoService } from '../../../core/voto/voto.service';
import { HttpResponse, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'jhi-scheda-statistica',
  templateUrl: './scheda-statistica.component.html',
  styleUrls: ['./scheda-statistica.component.scss']
})
export class SchedaStatisticaComponent implements OnInit {

  ris!: any[];
  voti!: number[];
  users: User[] | null = null;
  allUsers!: number;
  predictVoto!: number;
  idScheda!: number;
  totalItems = 0;
  totalVoti = 0;

  constructor(
    private userService: UserService,
    private activatedRoute: ActivatedRoute,
    private votoService: VotoService
  ) { }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(
      sch => {
        this.idScheda = sch['id'];        
      },
      undefined,
      undefined
    );
    this.fnStat();
  }

  fnStat(): void{
    this.votoService.getArrNumVoti(String(this.idScheda)).subscribe(ris => {
      if(ris){
        this.ris = ris;
        this.totalVoti = this.ris[0] + this.ris[1] + this.ris[2]; 
        this.ris[0] = (this.ris[0]/this.totalVoti)*100;
        this.ris[1] = (this.ris[1]/this.totalVoti)*100;
        this.ris[2] = (this.ris[2]/this.totalVoti)*100;
      }
      
    });
    
  }
  
  fnPredict(): void{
    //N utenti si/(N utenti tot - N utenti invalido) 
    //N utenti no/(N utenti tot - N utenti invalido) 
    this.votoService.getArrNumVoti(String(this.idScheda)).subscribe(voti => {
        if(voti){
        this.voti = voti;
        this.userService.query().subscribe((res: HttpResponse<User[]>) => this.onSuccess(res.body, res.headers), undefined, () =>{
          if(this.users){
            this.allUsers = this.users.length;         
            if ((this.voti[0]/(this.allUsers - this.voti[2])) > (this.voti[1]/(this.allUsers - this.voti[2]))){
              this.predictVoto = 0;
            } else if((this.voti[0]/(this.allUsers - this.voti[2])) < (this.voti[1]/(this.allUsers - this.voti[2]))){
              this.predictVoto = 1;
            } else {
              this.predictVoto = 2;
            } 
          } 
        });  
      }
    });
  }

  private onSuccess(users: User[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.users = users;
  }

}
