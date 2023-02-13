import { FoodImages } from "./food-images";

export class Food {
    id: number;
    foodCategory: string;
    name: string;
    price: number;
    description: string;
    image: FoodImages[];

    public constructor(id: number, foodCategory: string, name: string, price: number, description: string, image: FoodImages[]) {
        this.id = id;
        this.foodCategory = foodCategory;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }


}