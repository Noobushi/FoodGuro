import { Component, OnInit } from '@angular/core';
import { FoodImages } from '../food-images';
import { CategoryService } from '../service/category.service';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../shopping-cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutList: ShoppingCartItem[] = [];

  total: number = 0;
  discount: number = 0;
  tempTotal: number = 0;
  imagePath: string = "";
  images: FoodImages[] = [];

  constructor(private shoppingCartService: ShoppingCartService, private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.checkoutList = this.shoppingCartService.shoppingCartList;

    // this.checkoutList.map(food => food.name).forEach(foodName => this.foodService.getAllImages(foodName).subscribe((value) => {
    //   this.images = value;
    //   console.log(this.images);
    //   this.imagePath = value[0].name;
    // }));

    if (this.checkoutList.length >= 1) {
      this.tempTotal = this.checkoutList.map(e => e.price * e.quantity).reduce((sum, next) => sum = sum + next);
    }
    this.discount = this.shoppingCartService.calculateDiscount(this.tempTotal);
    Math.round(this.total = this.tempTotal - (this.tempTotal * this.discount));
  }


  increase(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity++;
    this.ngOnInit();
  }

  decrease(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity--;
    if (shoppingCartItem.quantity === 0) {
      this.shoppingCartService.removeFromCart(shoppingCartItem);
      this.ngOnInit();
    }
    this.ngOnInit();
  }

}
