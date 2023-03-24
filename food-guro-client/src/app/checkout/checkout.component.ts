import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../classes/shopping-cart-item';
import { OrderService } from '../service/order.service';
import { AuthService } from '../service/auth.service';
import { Order } from '../classes/order';

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
  order: Order = new Order();

  constructor(private shoppingCartService: ShoppingCartService, private orderService: OrderService, private authService: AuthService) {

  }

  ngOnInit(): void {
    this.getCheckout();
    this.checkoutList = this.shoppingCartService.shoppingCartList;
    this.calculateTotal();

  }

  increase(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity++;
    this.shoppingCartService.saveCartList();
    this.calculateTotal();
  }

  decrease(shoppingCartItem: ShoppingCartItem) {
    shoppingCartItem.quantity--;
    if (shoppingCartItem.quantity === 0) {
      this.checkoutList = this.shoppingCartService.removeFromCart(shoppingCartItem);
    }
    this.shoppingCartService.saveCartList();
    this.calculateTotal();
  }

  calculateTotal(): void {
    this.tempTotal = this.checkoutList.map(e => e.price * e.quantity).reduce((sum, next) => sum = sum + next);
    this.discount = this.shoppingCartService.calculateDiscount(this.tempTotal);
    Math.round(this.total = this.tempTotal - (this.tempTotal * this.discount));
  }

  getCheckout(): ShoppingCartItem[] {
    const checkoutList = localStorage.getItem("checkoutList");
    return JSON.parse(checkoutList!);
  }

  removeItem(shoppingCartItem: ShoppingCartItem): void {
    this.checkoutList = this.shoppingCartService.removeFromCart(shoppingCartItem);
  }

  saveMyCart() {
    this.order.foods = this.checkoutList;
    console.log(this.authService.getUser().username)
    this.orderService.create(this.order, this.authService.getUser().username).subscribe(x => console.log(x));
    console.log(this.order);
  }
}
