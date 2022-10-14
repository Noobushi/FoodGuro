import { Food } from "./food";

export class Order{
    id!: number;
    username!: string;
    foods: Food[] = [];
    public constructor(){

    }
}