import { Injectable } from '@angular/core';
import { Food } from '../classes/food';
import { ShoppingCartItem } from '../classes/shopping-cart-item';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  shoppingCartList: ShoppingCartItem[] = [];
  discount: number = 0;
  foundFood: ShoppingCartItem[] = [];
  constructor() {
    const savedCardList = localStorage.getItem("shoppingCartList");
    if (savedCardList) {
      this.shoppingCartList = JSON.parse(savedCardList);
    }
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
    this.saveCartList();

  }

  removeFromCart(cartItem: ShoppingCartItem): ShoppingCartItem[] {
    this.shoppingCartList = this.shoppingCartList.filter(e => e.id !== cartItem.id);
    this.saveCartList();
    return this.shoppingCartList;
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

  getProduct(productId: number): ShoppingCartItem {
    return this.shoppingCartList.find(e => e.id == productId) as ShoppingCartItem;
  }

  saveCartList(): void {
    localStorage.setItem("shoppingCartList", JSON.stringify(this.shoppingCartList));
  }

  getCartList(): ShoppingCartItem[] {
    const savedCartList = localStorage.getItem('shoppingCartList');
    return savedCartList ? JSON.parse(savedCartList) : [];
  }

}

