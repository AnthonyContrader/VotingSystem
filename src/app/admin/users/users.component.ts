import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/service/user.service';
import { UserDTO } from 'src/dto/userdto';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: UserDTO[];
  usertoinsert: UserDTO = new UserDTO();

  constructor(private service: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers() {
    this.service.getAll().subscribe(users => {this.users = users}, undefined, null);
  }

  delete(user: UserDTO) {
    this.service.delete(user.id).subscribe(() => {this.getUsers()}, undefined, null);
  }

  update(user: UserDTO) {
    this.service.update(user).subscribe(() => {this.getUsers()}, undefined, null);
  }

  insert(user: UserDTO) {
    this.service.insert(user).subscribe(() => {this.getUsers()}, undefined, null);
  }

  clear(){
    this.usertoinsert = new UserDTO();
  }
}
