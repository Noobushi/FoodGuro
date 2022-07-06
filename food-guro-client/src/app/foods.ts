export interface Foods {
    id: number;
    name: string;
    price: number;
    ingredients: string;
}

// Replace with real FOOD objects from the database!!!
export const foods = [
    {
        id: 1,
        name: "Mashed potatoes",
        price: 4.99,
        ingredients: 'Russet potatoes, salt, butter, olive oil, black pepper'
    },
    {
        id: 2,
        name: "French fries",
        price: 2.99,
        ingredients: 'Russet potatoes, peanut oil, salt, pepper,chopped parsley'
    },
    {
        id: 3,
        name: "Potatoe chips",
        price: 3.99,
        ingredients: 'Russet potatoes, olive oil, salt, pepper, red powder, garlic'
    }
];

export function getFoodName(id:number){
    const chosenFood = foods.find(e => e.id === id);
    return chosenFood===undefined?"":chosenFood.name;
   }
  

