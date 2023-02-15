import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { Food } from '../classes/food';
import { CategoryService } from '../service/category.service';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { TransferService } from '../service/transfer-service';

@Component({
  selector: 'app-category-products',
  templateUrl: './category-products.component.html',
  styleUrls: ['./category-products.component.css']
})
export class CategoryProductsComponent implements OnInit {

  public foods!: Food[];
  public categoryName: String = "";
  constructor(private categoryService: CategoryService, private shoppingCartService: ShoppingCartService, private transferService: TransferService, private notifierService: NotifierService) {
  }

  ngOnInit(): void {
    this.categoryName = this.transferService.retrieveString();
    this.categoryService.getFoodsInCategory(this.categoryName).subscribe(value => this.foods = value);
  }

  addFood(food: any) {
    this.shoppingCartService.addToCart(food);
    this.notifierService.notify("success", `${food.name} added to the cart!`)
  }

}
