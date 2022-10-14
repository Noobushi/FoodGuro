import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Food } from '../food';
import { ShoppingCartItem } from '../shopping-cart-item';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  shoppingCartList: ShoppingCartItem[];
  constructor(private http: HttpClient) {

    this.shoppingCartList = [];
  }

  addToCart(food:Food){
    const foundFood:ShoppingCartItem[] = this.shoppingCartList.filter(product => product.id === food.id)
    if(foundFood.length === 0){
      const newCartItem = new ShoppingCartItem(food.id,food.foodCategory,food.name,food.price,food.description);
      this.shoppingCartList.push(newCartItem);
    }
    else{
      foundFood[0].quantity++;
    }
  }

}

