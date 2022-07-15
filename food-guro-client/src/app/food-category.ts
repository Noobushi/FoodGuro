import { Food } from "./food";

export interface FoodCategory{
    id: number;
    name: string;
    foods: Food[];

}