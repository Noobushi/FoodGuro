import { Food } from "./food";

export class Order {
    foods: Food[] = [];
    public constructor(foods: Food[]) {
        this.foods = foods;
    }
}