import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  template: `
    <app-header></app-header>

    <router-outlet />

    <app-footer></app-footer>
  `,
  styles: []
})
export class AppComponent {
  title = 'suinapp';
  constructor(authService: AuthService) {
    authService.autoLogin();
  }
}
