import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { FoodService } from './service/food-service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
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
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { FoodTableComponent } from './food-table/food-table.component';
import { AuthInterceptor } from './auth.interceptor';
import { AuthService } from './service/auth.service';
import { UserTableComponent } from './user-table/user-table.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CategoryProductsComponent } from './category-products/category-products.component';
import { TransferService } from './service/transfer-service';
import { AppRoutingModule } from './app-routing.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';

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
    AppRoutingModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    NotifierModule.withConfig(customNotifierOptions),
    BrowserAnimationsModule,
    NgbModule,
    MatSidenavModule,
    MatGridListModule,
    MatCardModule
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    ListCategoryComponent,
    RegisterUserComponent,
    LoginUserComponent,
    CarouselComponent,
    AdminMenuComponent,
    FoodTableComponent,
    UserTableComponent,
    CheckoutComponent,
    ProductDetailsComponent,
    CategoryProductsComponent
  ],
  providers: [
    RouterModule,
    TransferService,
    FoodService,
    UserService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    AuthService
  ],
  exports: [CarouselComponent],

  bootstrap: [
    AppComponent,
    CarouselComponent
  ],
})
export class AppModule { }
