import { Food } from "./food";
import { FoodImages } from "./food-images";

export interface FoodCategory {
    id: number;
    name: string;
    image: FoodImages[];
    foods: Food[];
}