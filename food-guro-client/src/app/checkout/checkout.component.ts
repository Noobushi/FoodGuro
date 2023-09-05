import { Component, OnInit } from '@angular/core';
import { OrderService } from '../service/order.service';
import { AuthService } from '../service/auth.service';
import { Order } from '../classes/order';
import { Food } from '../classes/food';
import { FoodService } from '../service/food-service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  checkoutList: Food[] = [];
  total: number = 0;
  discount: number = 0;
  tempTotal: number = 0;
  order: Order = new Order([]);

  constructor(private foodService: FoodService, private orderService: OrderService, private authService: AuthService) {
  }

  ngOnInit(): void {
    this.orderService.retrieve(1).subscribe(value => {
      this.checkoutList = value;
    });
    this.calculateTotal();

  }

  increase(shoppingCartItem: Food) {
    shoppingCartItem.quantity++;
    this.saveMyCart();
    this.calculateTotal();
  }

  decrease(shoppingCartItem: Food) {
    shoppingCartItem.quantity--;
    if (shoppingCartItem.quantity === 0) {
      this.checkoutList = this.foodService.removeFromCart(shoppingCartItem);
    }
    this.saveMyCart();
    this.calculateTotal();
  }

  calculateTotal(): void {
    this.tempTotal = this.checkoutList.map(e => e.price * e.quantity).reduce((sum, next) => sum = sum + next);
    this.discount = this.foodService.calculateDiscount(this.tempTotal);
    Math.round(this.total = this.tempTotal - (this.tempTotal * this.discount));
  }

  removeItem(shoppingCartItem: Food): void {
    this.checkoutList = this.foodService.removeFromCart(shoppingCartItem);
    this.saveMyCart();
  }

  saveMyCart() {
    this.order.foods = this.checkoutList;
    this.orderService.create(this.order, this.authService.getUser().username).subscribe();
  }

}
