import { Item } from "../items/item"

export interface Cart{
    cartItems : CartItem[]
}

export interface CartItem{
    item : Item;
    quantity : number;
}