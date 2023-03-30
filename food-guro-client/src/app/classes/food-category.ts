import { Food } from "./food";

export interface FoodCategory {
    id: number;
    name: string;
    imagePath: string
    foods: Food[];
}