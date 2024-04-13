import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { CadastroPesoComponent } from './pages/cadastro-peso/cadastro-peso.component';
import { EdicaoPesoComponent } from './pages/edicao-peso/edicao-peso.component';
import { ListarSuinosComponent } from './pages/listar-suinos/listar-suinos.component';
import { PesagemComponent } from './pages/pesagem/pesagem.component';
import { CadastroSuinoComponent } from './pages/cadastro-suino/cadastro-suino.component';
import { EditarSuinosComponent } from './pages/editar-suinos/editar-suinos.component';
import { AuthGuard } from './core/auth.guard';
import { CadastroSessaoComponent } from './pages/cadastro-sessao/cadastro-sessao.component';
import { ListarSessoesComponent } from './pages/listar-sessoes/listar-sessoes.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'cadastro-peso', component: CadastroPesoComponent, canActivate: [AuthGuard]},
  { path: 'cadastro-peso/:id', component: CadastroPesoComponent, canActivate: [AuthGuard]},
  { path: 'edicao-peso/:id', component: EdicaoPesoComponent, canActivate: [AuthGuard]},
  { path: 'listar-suinos', component: ListarSuinosComponent, canActivate: [AuthGuard]},
  { path: 'pesagem/:id', component: PesagemComponent, canActivate: [AuthGuard]},
  { path: 'cadastrosuino', component: CadastroSuinoComponent, canActivate: [AuthGuard]},
  { path: 'editar-suino/:id', component: EditarSuinosComponent, canActivate: [AuthGuard]},
  { path: 'cadastro-sessao', component: CadastroSessaoComponent, canActivate: [AuthGuard]},
  { path: 'listar-sessoes', component: ListarSessoesComponent, canActivate: [AuthGuard]},
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
