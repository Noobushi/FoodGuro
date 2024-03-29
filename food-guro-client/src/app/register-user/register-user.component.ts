import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  firstName!: string;
  lastName!: string;
  city!: string;
  username!: string;
  password!: number;

  constructor(private userService: UserService, private notifierService: NotifierService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(userForm: any) {
    this.userService.register(userForm).subscribe(x => this.notifierService.notify("success", `User ${x.username} registered successfully!`));
    this.router.navigateByUrl('/');
  }

}
