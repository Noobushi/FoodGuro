import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NotifierService } from 'angular-notifier';
import { Food } from '../classes/food';
import { FoodService } from '../service/food-service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

  shoppingCartItem!: Food;
  imagePath: string = "";
  constructor(private route: ActivatedRoute, private foodService: FoodService, private notifierService: NotifierService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => { this.handleProductDetails(); })
    this.foodService.getImage(this.shoppingCartItem.name).subscribe((name) => {
      this.imagePath = name;
    });
  }

  handleProductDetails() {
    const theProductId: number = + this.route.snapshot.paramMap.get('id')!;
    this.shoppingCartItem = this.foodService.getProduct(theProductId);
  }

  addFood(shoppingCartItem: Food) {
    this.foodService.addToCart(shoppingCartItem);
    this.notifierService.notify("success", `${shoppingCartItem.name} added to the cart!`)
  }

}
