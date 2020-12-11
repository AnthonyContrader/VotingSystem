import { Component, OnInit } from '@angular/core';
import { Scheda } from '../../core/scheda/scheda.model';
import { SchedaService } from '../../core/scheda/scheda.service';
import { Account } from '../../core/user/account.model';
import { User } from '../../core/user/user.model';
import { UserService } from '../../core/user/user.service';
import { AccountService } from '../../core/auth/account.service';




@Component({
  selector: 'jhi-schede-lista',
  templateUrl: './schede-lista.component.html',
  styleUrls: ['./schede-lista.component.scss']
})
export class SchedeListaComponent implements OnInit {

  listaSchede?: Scheda[] | null = [];
  totalItems = 0;
  itemsPerPage: number;
  page: number;
  user: User;
  account!: Account;
  login!: string;
  
  constructor(
    private service: SchedaService,
    private userService: UserService,
    private accountService: AccountService
    
  ) {
    this.page = 0;
    this.itemsPerPage = 10;
    this.user = new User();
    
   }

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => {
      if (account) {
        this.account = account;
        this.userService.find(account.login).subscribe(user => {
          if(user){
            this.user = user;
            this.getList();
          }
        })
      }
    });
    
    
  }

  getList(): void{
    this.service.getSchedeListWithAlsoVoteUser(this.user.id).subscribe((schede) => {this.listaSchede = schede});
  }
}
