
export class Food {
    id: number;
    category: string;
    name: string;
    price: number;
    description: string;
    imagePath: string;
    quantity: number;
    public constructor(id: number, category: string, name: string, price: number, description: string, imagePath: string, quantity: number) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
        this.quantity = quantity;
    }


}