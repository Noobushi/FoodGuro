
export class ShoppingCartItem {
    quantity: number;
    id: number;
    category: string;
    name: string;
    price: number;
    description: string;
    imagePath: string;
    public constructor(id: number, category: string, name: string, price: number, description: string, imagePath: string) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = 1;
        this.imagePath = imagePath;
    }
}
