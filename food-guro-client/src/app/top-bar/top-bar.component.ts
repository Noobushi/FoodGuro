import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { User } from '../user';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  isLoggedIn!: boolean;
  user!: User | null
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.userSubject.subscribe(u => {
      this.user = u;
    });
    this.authService.isLoginSubject.subscribe(u => {
      this.isLoggedIn = u;
    });
  }

  logMeOut() {
    this.authService.logout();
  }

  isAdmin(): boolean {
    if (this.user?.userRole.includes('ROLE_ADMIN')) {
      return true;
    }
    return false;
  }

}
