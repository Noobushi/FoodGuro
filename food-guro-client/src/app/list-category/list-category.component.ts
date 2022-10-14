import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';
import { CategoryService } from '../service/category.service';
import { FoodService } from '../service/food-service';
import { OrderService } from '../service/order.service';
import { Order } from '../order';
import { Food } from '../food';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {
  food: Food = new Food()
  order: Order = new Order();
public categories$!: Observable<FoodCategory[]>;
  constructor(private foodService: FoodService, private categoryService: CategoryService, private orderService: OrderService) { }

  ngOnInit(): void {
    this.categories$ = this.foodService.getFoods();
  }
  
  addFood() {
    const user = localStorage.getItem('user');
    const userObj = JSON.parse(user || '{}');
    const username = userObj.username;
    let foodName = document.getElementById('fname')?.innerText;
    this.food.name = foodName!;
    let foodDesc = document.getElementById('fdesc')?.innerText;
    this.food.description = foodDesc!;
    let foodPrice = document.getElementById('fprice')?.innerText;
    this.food.price = Number(foodPrice)!;
    this.order.foods.push(this.food);
    this.order.username = username;
    this.orderService.create(this.order);
  }

}
