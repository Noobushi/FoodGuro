import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../user';
import { NotifierService } from 'angular-notifier';
import { AuthService } from '../service/auth.service';
import { take } from 'rxjs';

@Component({
  selector: 'app-login-user',
  templateUrl: './login-user.component.html',
  styleUrls: ['./login-user.component.css']
})
export class LoginUserComponent implements OnInit {
  showPassword: boolean = false;
  usernameField: any;

  constructor(private router: Router, private authService: AuthService, private notifierservice: NotifierService) {
  }

  ngOnInit(): void {

  }

  onSubmit(loginForm: any) {
    this.authService.login(loginForm).pipe(take(1)).subscribe({
      next: (response: HttpResponse<User>) => {
        const token = response.headers.get("Jwt-Token")!;
        this.authService.saveJwtToken(token);
        this.authService.saveUser(response.body!);
        this.router.navigateByUrl('/');
        const user = response.body! as User | null;
        this.authService.userSubject.next(user);
        this.authService.isLoginSubject.next(true);
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 403) {
          this.notifierservice.notify("warning", `Invalid username or password!`)
        }
      }
    });

    this.authService.login(loginForm).subscribe(user => this.notifierservice.notify("success", `Welcome back, ${this.usernameField}!`));
  }

}




