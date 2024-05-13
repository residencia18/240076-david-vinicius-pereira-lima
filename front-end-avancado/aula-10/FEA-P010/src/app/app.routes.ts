import { Routes } from '@angular/router';
import { CartTotalComponent } from './cart-total/cart-total.component';

export const routes: Routes = [
    
    {path: 'cart-total', 
        loadComponent:() =>
            import('./cart-total/cart-total.component').then(c => c.CartTotalComponent)
    }
];
