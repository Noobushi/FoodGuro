import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { FoodService } from '../service/food-service';
import { ShoppingCartService } from '../service/shopping-cart.service';
import { ShoppingCartItem } from '../classes/shopping-cart-item';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  shoppingCartItem!: ShoppingCartItem;
  imagePath: string = "";
  constructor(private shoppingCartService: ShoppingCartService, private route: ActivatedRoute, private foodService: FoodService, private notifierService: NotifierService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => { this.handleProductDetails(); })
    this.foodService.getImage(this.shoppingCartItem.name).subscribe((name) => {
      this.imagePath = name;
      console.log(name)
    });
  }

  handleProductDetails() {
    const theProductId: number = + this.route.snapshot.paramMap.get('id')!;
    this.shoppingCartItem = this.shoppingCartService.getProduct(theProductId);
  }

  addFood(shoppingCartItem: ShoppingCartItem) {
    this.shoppingCartService.addToCart(shoppingCartItem);
    this.notifierService.notify("success", `${shoppingCartItem.name} added to the cart!`)
  }

}
