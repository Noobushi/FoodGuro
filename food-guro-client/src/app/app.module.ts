import { NgModule } from '@angular/core';


import { RouterModule } from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { FoodAlertsComponent } from './food-alerts/food-alerts.component';
import { FoodService } from './service/food-service';
import {HttpClientModule} from '@angular/common/http';
import { ListCategoryComponent } from './list-category/list-category.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { CommonModule } from '@angular/common';
import { LoginUserComponent } from './login-user/login-user.component';
import { UserService } from './service/user.service';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarouselComponent } from './carousel/carousel.component';


const customNotifierOptions: NotifierOptions = {
  position: {
        horizontal: {
            position: 'right'
        },
        vertical: {
            position: 'bottom',

        }
    },
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    NotifierModule.withConfig(customNotifierOptions),
    BrowserAnimationsModule,
    RouterModule.forRoot([
      { path: '', component: CarouselComponent },
      {path: 'register',component: RegisterUserComponent},
      {path: 'login',component: LoginUserComponent},
      {path: 'category',component: ListCategoryComponent}

    ]),
    NgbModule
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    ListCategoryComponent,
    FoodAlertsComponent,
    RegisterUserComponent,
    LoginUserComponent,
    CarouselComponent,
  ],
  providers: [
    FoodService,
    UserService
  ],
  exports: [CarouselComponent],

  bootstrap: [
    AppComponent,
    CarouselComponent
  ]
})
export class AppModule { }
