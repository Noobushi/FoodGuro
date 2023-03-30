import { Injectable } from '@angular/core';
import { Food } from '../classes/food';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  shoppingCartList: Food[] = [];
  discount: number = 0;
  foundFood: Food[] = [];
  constructor() {
    const savedCardList = localStorage.getItem("shoppingCartList");
    if (savedCardList) {
      this.shoppingCartList = JSON.parse(savedCardList);
    }
  }

  addToCart(food: Food) {
    this.foundFood = this.shoppingCartList.filter(product => product.id === food.id)
    if (this.foundFood.length === 0) {
      const newCartItem = new Food(food.id, food.category, food.name, food.price, food.description, food.imagePath, food.quantity);
      this.shoppingCartList.push(newCartItem);
    }
    else {
      this.foundFood[0].quantity++;
    }
    this.saveCartList();

  }

  removeFromCart(cartItem: Food): Food[] {
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

  getProduct(productId: number): Food {
    return this.shoppingCartList.find(e => e.id == productId) as Food;
  }

  saveCartList(): void {
    localStorage.setItem("shoppingCartList", JSON.stringify(this.shoppingCartList));
  }

  getCartList(): Food[] {
    const savedCartList = localStorage.getItem('shoppingCartList');
    return savedCartList ? JSON.parse(savedCartList) : [];
  }

}

