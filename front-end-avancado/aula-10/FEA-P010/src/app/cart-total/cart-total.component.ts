import { Component, inject } from '@angular/core';
import { CartServiceService } from '../services/cart-service.service';

@Component({
  selector: 'app-cart-total',
  standalone: true,
  imports: [],
  templateUrl: './cart-total.component.html',
  styleUrl: './cart-total.component.css'
})
export class CartTotalComponent {
  cartService = inject(CartServiceService);
  cartItems = this.cartService.cartItems;
  subTotal = this.cartService.subTotal;
}
