import { Injectable } from '@angular/core';
import { Food } from '../classes/food';
import { ShoppingCartItem } from '../classes/shopping-cart-item';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  shoppingCartList: ShoppingCartItem[];
  discount: number = 0;
  foundFood: ShoppingCartItem[] = [];
  constructor() {
    this.shoppingCartList = [];
  }

  addToCart(food: Food) {
    this.foundFood = this.shoppingCartList.filter(product => product.id === food.id)
    if (this.foundFood.length === 0) {
      const newCartItem = new ShoppingCartItem(food.id, food.foodCategory, food.name, food.price, food.description, food.image);
      this.shoppingCartList.push(newCartItem);

    }
    else {
      this.foundFood[0].quantity++;
    }

  }

  removeFromCart(cartItem: ShoppingCartItem) {
    this.shoppingCartList = this.shoppingCartList.filter(e => e.id !== cartItem.id);
  }

  calculateDiscount(total: number) {
    if (total < 50) {
      return this.discount = 0;
    }
    else if (total > 50 && total < 100) {
      return this.discount = 0.1;
    }
    else {
      return this.discount = 0.2;
    }
  }

  getProduct(productId: number) {
    return this.shoppingCartList.filter(e => e.id == productId);
  }


}

