import { Component, OnInit } from '@angular/core';
import { Food } from '../food';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../shopping-cart-item';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  checkoutList: ShoppingCartItem[];

  constructor(private shoppingCartService : ShoppingCartService) {
    this.checkoutList = [];
  }

  ngOnInit(): void {
    this.checkoutList = this.shoppingCartService.shoppingCartList;
  }

}
