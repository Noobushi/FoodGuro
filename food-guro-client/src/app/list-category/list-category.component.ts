import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { Observable } from 'rxjs';
import { FoodCategory } from '../classes/food-category';
import { CategoryService } from '../service/category.service';
import { FoodService } from '../service/food-service';
import { TransferService } from '../service/transfer-service';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {

  public categories$!: Observable<FoodCategory[]>;

  constructor(private categoryService: CategoryService, private foodService: FoodService, private transferService: TransferService, private notifierService: NotifierService) { }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getAll();
  }

  addFood(food: any) {
    this.foodService.addToCart(food);
    this.notifierService.notify("success", `${food.name} added to the cart!`);
  }

  getCategory(name: String) {
    this.transferService.saveString(name);
  }

}
