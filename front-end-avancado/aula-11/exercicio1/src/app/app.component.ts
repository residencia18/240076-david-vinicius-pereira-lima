import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SaidaContadorComponent } from './saida-contador/saida-contador.component';
import { ControlesContadorComponent } from './controles-contador/controles-contador.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, SaidaContadorComponent, ControlesContadorComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'exercicio1';
}
