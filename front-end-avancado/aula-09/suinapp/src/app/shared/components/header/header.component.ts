import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  menuItems: MenuItem[] = [];

  ngOnInit() {
    this.configurarMenu();
  }

  configurarMenu() {
    this.menuItems = [
      {
        label: 'Home',
        routerLink: '/',
        icon: 'pi pi-home'
      },
      {
        label: 'Cadastro',
        items: [
          { label: 'Suínos', routerLink: '/suino/cadastro-suino' },
          { label: 'Peso', routerLink: '/peso/cadastro-peso' },
          { label: 'Sessões', routerLink: '/sessao/cadastro-sessao' }
        ]
      },
      {
        label: 'Listar',
        items: [
          { label: 'Suínos', routerLink: '/suino/listar-suinos' },
          { label: 'Sessões', routerLink: '/sessao/listar-sessoes' }
        ]
      },
      {
        label: 'Histórico',
        routerLink: '/suino/historico-animal',
        icon: 'pi pi-history'
      },
      {
        label: 'Login',
        routerLink: '/login',
        icon: 'pi pi-sign-in'
      }
    ];
  }
}
