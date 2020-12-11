import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Scheda } from '../../core/scheda/scheda.model';
import { SchedaService } from '../../core/scheda/scheda.service';
import { Account } from '../../core/user/account.model';
import { User } from '../../core/user/user.model';
import { UserService } from '../../core/user/user.service';
import { AccountService } from '../../core/auth/account.service';
import { Voto } from '../../core/voto/voto.model';
import { VotoService } from '../../core/voto/voto.service';




@Component({
  selector: 'jhi-scheda-voto',
  templateUrl: './scheda-voto.component.html',
  styleUrls: ['./scheda-voto.component.scss']
})
export class SchedaVotoComponent implements OnInit {

  scheda!: Scheda;
  user!: User;
  account!: Account;
  voto!: Voto;
  idScheda!: number;
  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private accountService: AccountService,
    private userService: UserService,
    private schedaService: SchedaService,
    private votoService: VotoService
  ) { 
    this.scheda = new Scheda();
    this.user = new User();
    this.voto = new Voto(); 
   }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe(
      sch => {
        if(sch){
          this.idScheda = sch['idScheda'];
          this.voto.scheda = sch['idScheda']; 
          this.getScheda(this.idScheda);
          this.accountService.identity().subscribe(account => {
            if (account) {
              this.account = account;
              this.userService.find(account.login).subscribe(user => {
                if(user){
                  this.user = user;
                  this.voto.utente = this.user.id;
                }
              })
            }
          }); 
        }
      }
    );
   
  }

  getScheda(idScheda: number): void{
    this.schedaService.read(String(idScheda)).subscribe((scheda) => {
      if(scheda){
        this.scheda = scheda;
      }
    },
    undefined,
    undefined
    );
  }

  submit(votoToInsert: Voto): void {
    this.votoService.create(votoToInsert).subscribe(undefined, undefined, undefined);
    this.router.navigate(['../../user/schede-lista']);
  }
}
