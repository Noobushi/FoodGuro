import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { FoodCategory } from '../food-category';
import { CategoryService } from '../service/category.service';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { TransferService } from '../service/transfer-service';

@Component({
  selector: 'app-category-potatoe',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {

  public categories$!: Observable<FoodCategory[]>;

  constructor(private categoryService: CategoryService, private shoppingCartService: ShoppingCartService, private transferService: TransferService) { }

  ngOnInit(): void {
    this.categories$ = this.categoryService.getAll();

  }

  addFood(food: any) {
    this.shoppingCartService.addToCart(food);
  }

  getCategory(name: String) {
    this.transferService.saveString(name);
  }


}
