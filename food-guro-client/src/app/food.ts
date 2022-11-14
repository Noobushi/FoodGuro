export class Food {
    id: number;
    foodCategory: string;
    name: string;
    price: number;
    description: string;

    public constructor(id: number, foodCategory: string, name: string, price: number, description: string) {
        this.id = id;
        this.foodCategory = foodCategory;
        this.name = name;
        this.price = price;
        this.description = description;
    }


}