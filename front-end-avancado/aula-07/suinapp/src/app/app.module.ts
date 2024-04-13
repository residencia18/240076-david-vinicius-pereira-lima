import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http'

import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { DropdownModule } from 'primeng/dropdown';
import { LoginComponent } from './pages/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { RippleModule } from 'primeng/ripple';
import { MenubarModule } from 'primeng/menubar';
import { HeaderComponent } from './shared/components/header/header.component';
import { FooterComponent } from './shared/components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { PanelModule } from 'primeng/panel';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ListarSuinosComponent } from './pages/listar-suinos/listar-suinos.component';
import { CadastroSuinoComponent } from './pages/cadastro-suino/cadastro-suino.component';
import { AuthInterceptor } from './core/auth.interceptor';
import { CadastroPesoComponent } from './pages/cadastro-peso/cadastro-peso.component';
import { CalendarModule } from 'primeng/calendar';
import { EdicaoPesoComponent } from './pages/edicao-peso/edicao-peso.component';
import { CalcularIdadeMesesPipe } from './shared/pipes/calcular-idade-meses.pipe';
import { PesagemComponent } from './pages/pesagem/pesagem.component';
import { ChartModule } from 'primeng/chart';
import { TableModule } from 'primeng/table';
import { EditarSuinosComponent } from './pages/editar-suinos/editar-suinos.component';
import { MessagesModule } from 'primeng/messages';
import { CadastroSessaoComponent } from './pages/cadastro-sessao/cadastro-sessao.component';
import { MultiSelectModule } from 'primeng/multiselect';
import { ListarSessoesComponent } from './pages/listar-sessoes/listar-sessoes.component';
import { PaginatorModule } from 'primeng/paginator';
import { ConfirmationService } from 'primeng/api';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CadastroPesoComponent,
    EdicaoPesoComponent,
    ListarSuinosComponent,
    CalcularIdadeMesesPipe,
    PesagemComponent,
    CadastroSuinoComponent,
    EditarSuinosComponent,
    CadastroSessaoComponent,
    ListarSessoesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ButtonModule,
    CheckboxModule,
    InputTextModule,
    RippleModule,
    MenubarModule,
    PanelModule,
    CalendarModule,
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    FormsModule,
    ChartModule,
    BrowserModule,
    DropdownModule,
    TableModule,
    ChartModule,
    MessagesModule,
    MultiSelectModule,
    PaginatorModule,
    ConfirmDialogModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}, ConfirmationService],
  bootstrap: [AppComponent]
})
export class AppModule { }
