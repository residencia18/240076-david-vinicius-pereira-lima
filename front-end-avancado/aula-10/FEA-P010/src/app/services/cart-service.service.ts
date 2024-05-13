import { Injectable, computed, signal } from '@angular/core';
import { Item } from '../items/item';
import { CartItem } from '../cart/cart';

@Injectable({
  providedIn: 'root'
})
export class CartServiceService {

  cartItems = signal<CartItem[]>([]);

  subTotal = computed(() => this.cartItems().reduce((a, b) =>
    a + (b.quantity * Number(b.item.price)), 0
  ));

  addToCart(item: Item): void {
    const index = this.cartItems().findIndex(i =>
      i.item.name === item.name
    );
    if (index === -1) {
      this.cartItems.update(items => [...items, { item, quantity: 1 }]);
    }
    else {
      this.cartItems.update(items =>
        [
          ...items.slice(0, index),
          { ...items[index], quantity: items[index].quantity + 1 },
          ...items.slice(index + 1)
        ]
      );
    }
  }

  removeFromCart(cartItem: CartItem): void {
    this.cartItems.update(items => items.filter(i =>
      i.item.name !== this.cartItems.name
    ));
  }

  updateInCart(cartItem: CartItem, quantity: number) {
    this.cartItems.update(items =>
      items.map(i => i.item.name === cartItem.item.name ?
        { item: cartItem.item, quantity } : i)
    );
  }
}
