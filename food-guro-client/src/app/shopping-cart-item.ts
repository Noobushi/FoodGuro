import { FoodImages } from "./food-images";

export class ShoppingCartItem {
    quantity: number;
    id: number;
    foodCategory: string;
    name: string;
    price: number;
    description: string;
    images: FoodImages[];
    public constructor(id: number, foodCategory: string, name: string, price: number, description: string, images: FoodImages[]) {
        this.id = id;
        this.foodCategory = foodCategory;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = 1;
        this.images = images;
    }
}
