import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CadastroPesoComponent } from './cadastro-peso/cadastro-peso.component';
import { EdicaoPesoComponent } from './edicao-peso/edicao-peso.component';
import { PesagemComponent } from './pesagem/pesagem.component';
import { PanelModule } from 'primeng/panel';
import { MessagesModule } from 'primeng/messages';
import { InputTextModule } from 'primeng/inputtext';
import { CalendarModule } from 'primeng/calendar';
import { ButtonModule } from 'primeng/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartModule } from 'primeng/chart';
import { RouterModule, Routes } from '@angular/router';
import { DynamicDialogConfig } from 'primeng/dynamicdialog';

const routes : Routes = [
  { path: 'cadastro-peso', component: CadastroPesoComponent },
  { path: 'cadastro-peso/:id', component: CadastroPesoComponent },
  { path: 'edicao-peso/:id', component: EdicaoPesoComponent },
  { path: 'pesagem/:id', component: PesagemComponent }
];

@NgModule({
  declarations: [
    CadastroPesoComponent,
    EdicaoPesoComponent,
    PesagemComponent
  ],
  imports: [
    CommonModule,
    PanelModule,
    MessagesModule,
    InputTextModule,
    CalendarModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    ChartModule,
    RouterModule.forChild(routes)
  ],
  providers: [DynamicDialogConfig]
})
export class PesoModule { }
