import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { Food } from '../classes/food';
import { CategoryService } from '../service/category.service';
import { FoodService } from '../service/food-service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-category-products',
  templateUrl: './category-products.component.html',
  styleUrls: ['./category-products.component.css']
})
export class CategoryProductsComponent implements OnInit {

  foods!: Food[];
  categoryName: string = "";
  shoppingCartItem!: Food;
  imagePath: string[] = [];
  constructor(private categoryService: CategoryService, private foodService: FoodService, private notifierService: NotifierService,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.categoryName = params['foodCategory'];
    })
    this.categoryService.getFoodsInCategory(this.categoryName).subscribe((value) => {
      this.foods = value;
      for (let i = 0; i < value.length; i++) {
        this.imagePath[i] = value[i].imagePath;
      }
    });
  }

  addFood(food: any) {
    this.foodService.addToCart(food);
    this.notifierService.notify("success", `${food.name} added to the cart!`)
  }

}
