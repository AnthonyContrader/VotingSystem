import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserDTO } from 'src/dto/userdto';
import { UserService } from '../../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  user: UserDTO;
  
  constructor(private service: UserService, private route: Router) { }

  ngOnInit() {
  }
  
  signUp(f: NgForm): void {
    this.user = new UserDTO();
    this.user.username = f.value.username;
    this.user.password = f.value.password;
  
    this.user.usertype = "USER";
  
    this.service.insert(this.user).subscribe(null,undefined,null);
    this.route.navigate(['login']);
  }



}
