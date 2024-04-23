import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './core/auth.guard';

const routes: Routes = [
  { path: 'login', loadChildren: () => import('./pages/login/login.module').then(m => m.LoginModule) },
  { path: 'home', component: HomeComponent },
  { path: 'peso', loadChildren: () => import('./pages/peso/peso.module').then(m => m.PesoModule), canActivate: [AuthGuard] },
  { path: 'suino', loadChildren: () => import('./pages/suino/suino.module').then(m => m.SuinoModule), canActivate: [AuthGuard] },
  { path: 'sessao', loadChildren: () => import('./pages/sessao/sessao.module').then(m => m.SessaoModule), canActivate: [AuthGuard] },
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
