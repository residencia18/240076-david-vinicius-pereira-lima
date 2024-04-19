import { AsyncPipe, CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { selectorContador } from '../store/contador.selectors';

@Component({
  selector: 'app-saida-contador',
  standalone: true,
  imports: [CommonModule, AsyncPipe],
  templateUrl: './saida-contador.component.html',
  styleUrl: './saida-contador.component.css'
})
export class SaidaContadorComponent {
  contador$:Observable<number>;
  constructor(private store:Store<{contador:number}>){
    this.contador$ = store.select(selectorContador);
  }
}
