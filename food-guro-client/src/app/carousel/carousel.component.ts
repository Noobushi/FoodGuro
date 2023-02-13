import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { User } from '../classes/user';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.css']
})
export class CarouselComponent implements OnInit {
  isLoggedIn!: boolean;
  user!: User | null

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.userSubject.subscribe(u => {
      this.user = u;
    });
    this.authService.isLoginSubject.subscribe(u => {
      this.isLoggedIn = u;
    });
  }

}
