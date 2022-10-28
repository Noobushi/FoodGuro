import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';
import { FoodService } from '../service/food-service';
import { ShoppingCartService } from '../service/shopping-cart.service';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {
  public categories$!: Observable<FoodCategory[]>;
  constructor(private foodService: FoodService, private shoppingCartService: ShoppingCartService) { }

  ngOnInit(): void {
    this.categories$ = this.foodService.getFoods();
  }

  addFood(food: any) {
    this.shoppingCartService.addToCart(food);
  }

}
