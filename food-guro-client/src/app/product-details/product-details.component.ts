import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../shopping-cart-item';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  shoppingCartItem!: ShoppingCartItem;

  constructor(private shoppingCartService: ShoppingCartService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => { this.handleProductDetails(); })

  }
  handleProductDetails() {
    const theProductId: number = + this.route.snapshot.paramMap.get('id')!;
    const result = this.shoppingCartService.getProduct(theProductId);
    this.shoppingCartItem = result[0];
  }

  addFood(shoppingCartItem: ShoppingCartItem) {
    this.shoppingCartService.addToCart(shoppingCartItem);
  }

}
