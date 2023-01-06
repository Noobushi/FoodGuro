import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { User } from '../user';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent implements OnInit {
  isLoggedIn!: boolean;
  user!: User | null;
  isAnnoyed: boolean = false;
  counter: number = 0;
  logoImageIcon: string = "assets/pictures/guro-logo.png";

  constructor(private authService: AuthService) { }

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

  clickCounter() {
    this.counter++;
    if (this.counter > 6) {
      this.isAnnoyed = true;
    }
    switch (this.counter) {
      case 1: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageOne.png";
        break;
      }
      case 2: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageTwo.png";
        break;
      }
      case 3: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageThree.png";
        break;
      }
      case 4: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageFour.png";
        break;
      }
      case 5: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageFive.png";
        break;
      }
      case 6: {
        this.logoImageIcon = "assets/pictures/angryGuroLogoStageSix.png";
        break;
      }
    }
  }

}
