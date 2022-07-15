import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { ToastrModule } from 'ngx-toastr';

import { FoodAlertsComponent } from './food-alerts/food-alerts.component';
import { FoodService } from './service/food-service';
import {HttpClientModule} from '@angular/common/http';
import { ListCategoryComponent } from './list-category/list-category.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { CommonModule } from '@angular/common';
import { LoginUserComponent } from './login-user/login-user.component';
import { UserService } from './service/user.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NotifierModule, NotifierOptions } from 'angular-notifier';

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
    ToastrModule.forRoot(),
    RouterModule.forRoot([
      { path: '', component: ListCategoryComponent },
      {path: 'register',component: RegisterUserComponent},
      {path: 'login',component: LoginUserComponent}
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    ListCategoryComponent,
    FoodAlertsComponent,
    RegisterUserComponent,
    LoginUserComponent,
   
  ],
  providers: [
    FoodService,
    UserService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }

