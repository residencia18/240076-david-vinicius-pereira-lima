import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { incrementar, decrementar } from '../store/contador.actions';

@Component({
  selector: 'app-controles-contador',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './controles-contador.component.html',
  styleUrl: './controles-contador.component.css'
})
export class ControlesContadorComponent {
  constructor(private store:Store){}
  incrementar(){
    this.store.dispatch(incrementar({valor:20}));
  }
  decrementar(){
    this.store.dispatch(decrementar({valor:20}));
  }
}
