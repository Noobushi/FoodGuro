import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminMenuComponent } from './admin-menu/admin-menu.component';
import { CarouselComponent } from './carousel/carousel.component';
import { CategoryProductsComponent } from './category-products/category-products.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ListCategoryComponent } from './list-category/list-category.component';
import { LoginUserComponent } from './login-user/login-user.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { RegisterUserComponent } from './register-user/register-user.component';

const routes: Routes = [
  { path: '', component: CarouselComponent },
  { path: 'register', component: RegisterUserComponent },
  { path: 'login', component: LoginUserComponent },
  { path: 'category', component: ListCategoryComponent },
  { path: 'adminMenu', component: AdminMenuComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'productDetails/:id', component: ProductDetailsComponent },
  { path: 'categoryProducts', component: CategoryProductsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
