import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryProductsComponent } from './category-products/category-products.component';
import { ListCategoryComponent } from './list-category/list-category.component';

const routes: Routes = [
  {
    path: 'listCategory',
    component: ListCategoryComponent
  },
  {
    path: 'categoryProducts',
    component: CategoryProductsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
