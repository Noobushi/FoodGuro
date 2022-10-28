import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../shopping-cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutList: ShoppingCartItem[];
  total: any;
  discount: any;
  tempTotal: number = 0;
  constructor(private shoppingCartService: ShoppingCartService) {
    this.checkoutList = [];
  }

  ngOnInit(): void {
    this.checkoutList = this.shoppingCartService.shoppingCartList;
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
