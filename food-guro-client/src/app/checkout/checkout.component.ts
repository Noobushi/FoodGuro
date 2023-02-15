import { Component, OnDestroy, OnInit } from '@angular/core';
import { FoodImages } from '../classes/food-images';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../classes/shopping-cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit, OnDestroy {

  checkoutList: ShoppingCartItem[] = [];

  total: number = 0;
  discount: number = 0;
  tempTotal: number = 0;
  imagePath: string = "";
  images: FoodImages[] = [];

  constructor(private shoppingCartService: ShoppingCartService) {
  }
  ngOnDestroy(): void {
    this.saveCheckout(this.checkoutList);
  }

  ngOnInit(): void {
    this.getCheckout();
    this.checkoutList = this.shoppingCartService.shoppingCartList;
    this.calculateTotal();


    // this.checkoutList.map(food => food.name).forEach(foodName => this.foodService.getAllImages(foodName).subscribe((value) => {
    //   this.images = value;
    //   console.log(this.images);
    //   this.imagePath = value[0].name;
    // }));

  }

  increase(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity++;
    this.calculateTotal();
  }

  decrease(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity--;
    if (shoppingCartItem.quantity === 0) {
      this.checkoutList = this.shoppingCartService.removeFromCart(shoppingCartItem);
    }
    this.calculateTotal();
  }

  calculateTotal(): void {
    this.tempTotal = this.checkoutList.map(e => e.price * e.quantity).reduce((sum, next) => sum = sum + next);
    this.discount = this.shoppingCartService.calculateDiscount(this.tempTotal);
    Math.round(this.total = this.tempTotal - (this.tempTotal * this.discount));
  }

  saveCheckout(checkoutList: ShoppingCartItem[]) {
    localStorage.setItem("checkoutList", JSON.stringify(checkoutList));
  }

  getCheckout(): ShoppingCartItem[] {
    const checkoutList = localStorage.getItem("checkoutList");
    return JSON.parse(checkoutList!);
  }

}
