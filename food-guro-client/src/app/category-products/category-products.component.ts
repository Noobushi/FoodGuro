import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Food } from '../food';
import { FoodCategory } from '../food-category';
import { CategoryService } from '../service/category.service';
import { FoodService } from '../service/food-service';
import { ShoppingCartService } from '../service/shopping-cart.service';

@Component({
  selector: 'app-category-products',
  templateUrl: './category-products.component.html',
  styleUrls: ['./category-products.component.css']
})
export class CategoryProductsComponent implements OnInit {
  public categories!: Observable<FoodCategory[]>;
  public foods!: Observable<Food[]>;
  constructor(private foodService: FoodService, private shoppingCartService: ShoppingCartService, private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categories = this.categoryService.getAll();
    console.log(this.categoryService.getAll());
    this.foods = this.foodService.getFoodsInCategory(this.categories);
    console.log(this.foodService.getFoodsInCategory(this.categories));
  }

  addFood(food: any) {
    this.shoppingCartService.addToCart(food);
  }
}
