import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { UserService } from '../service/user.service';


@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  constructor(private userService: UserService, private notifierService: NotifierService) { }

  ngOnInit(): void {
  }

  submitted = false;

  onSubmit(userForm:any) { 
    this.userService.register(userForm).subscribe(x=> this.notifierService.notify("success", `User ${x.username} registered successfully!`));
  }


}
