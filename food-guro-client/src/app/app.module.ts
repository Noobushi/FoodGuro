import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { CategoryPotatoeComponent } from './category-potatoes/category-potatoes.component';
import { FoodAlertsComponent } from './food-alerts/food-alerts.component';

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      { path: '', component: CategoryPotatoeComponent },
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    CategoryPotatoeComponent,
    FoodAlertsComponent
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }

