import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  items: MenuItem[] = [];

  ngOnInit() {
    this.items = [
      { label: 'Home', routerLink: '/' },
      { label: 'Login', routerLink: '/login' },
      { label: 'Cadastro Suinos', routerLink: '/cadastrosuino' },
      { label: 'Cadastro de Peso', routerLink: '/cadastro-peso' },
      { label: 'Cadastro Sessões', routerLink: '/cadastro-sessao' },
      { label: 'Listar Suinos', routerLink: '/listar-suinos' },
      { label: 'Listar Sessões', routerLink: '/listar-sessoes' }
      ];
  }
}