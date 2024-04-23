import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastroSessaoComponent } from './cadastro-sessao/cadastro-sessao.component';
import { ListarSessoesComponent } from './listar-sessoes/listar-sessoes.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MessagesModule } from 'primeng/messages';
import { PanelModule } from 'primeng/panel';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { PaginatorModule } from 'primeng/paginator';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { TableModule } from 'primeng/table';
import { MultiSelectModule } from 'primeng/multiselect';
import { RouterModule, Routes } from '@angular/router';

const routes : Routes = [
  { path: 'cadastro-sessao', component: CadastroSessaoComponent },
  { path: 'listar-sessoes', component: ListarSessoesComponent }
];

@NgModule({
  declarations: [
    CadastroSessaoComponent,
    ListarSessoesComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MessagesModule,
    PanelModule,
    CalendarModule,
    InputTextModule,
    PaginatorModule,
    ConfirmDialogModule,
    ButtonModule,
    CheckboxModule,
    MultiSelectModule,
    TableModule,
    RouterModule.forChild(routes)
  ]
})
export class SessaoModule { }
