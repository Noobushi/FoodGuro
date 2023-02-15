import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { FoodImages } from '../classes/food-images';
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
  images: FoodImages[] = [];
  imagePath: string = "";
  constructor(private shoppingCartService: ShoppingCartService, private route: ActivatedRoute, private foodService: FoodService, private notifierService: NotifierService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => { this.handleProductDetails(); })
    this.foodService.getAllImages(this.shoppingCartItem.name).subscribe({
      next: (value) => {
        this.images = value;
        this.imagePath = value[0].name;
      }
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
