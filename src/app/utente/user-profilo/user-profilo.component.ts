import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-user-profilo',
  templateUrl: './user-profilo.component.html',
  styleUrls: ['./user-profilo.component.css']
})
export class UserProfiloComponent implements OnInit {

  usertoupdate: UserDTO;
  constructor(
    private service: UserService
  ) { }

  ngOnInit() {
    this.usertoupdate = JSON.parse(localStorage.getItem('currentUser'));
  }

  submit(user: UserDTO){
    this.service.update(user).subscribe(usertoupdate => {this.usertoupdate = usertoupdate}, undefined, null);
  }

}
